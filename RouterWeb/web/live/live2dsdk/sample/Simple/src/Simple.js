/**
*  Sample.js
*
*  You can modify and use this source freely
*  only for the development of application related Live2D.
*
*  (c) Live2D Inc. All rights reserved.
*/


window.onerror = function(msg, url, line, col, error) {
    var errmsg = "file:" + url + "<br>line:" + line + " " + msg;
    Simple.myerror(errmsg);
}

var Simple = function() {

    
    this.live2DModel = null;
    
    
    this.requestID = null;
    
    
    this.loadLive2DCompleted = false;
    
    
    this.initLive2DCompleted = false;
    
    
    this.loadedImages = [];
    
    
    this.modelDef = {
        
        "type":"Live2D Model Setting",
        "name":"haru",
        "model":"assets/haru/haru.moc",
        "textures":[
            "assets/haru/haru.1024/texture_00.png",
            "assets/haru/haru.1024/texture_01.png",
            "assets/haru/haru.1024/texture_02.png"
        ]
    };
    
    
    Live2D.init();
    

    
	var canvas = document.getElementById("glcanvas");

	
    
	canvas.addEventListener("webglcontextlost", function(e) {
        Simple.myerror("context lost");
        loadLive2DCompleted = false;
        initLive2DCompleted = false;
        
        var cancelAnimationFrame = 
            window.cancelAnimationFrame || 
            window.mozCancelAnimationFrame;
        cancelAnimationFrame(requestID); 
        
        e.preventDefault(); 
    }, false);
    
    
	canvas.addEventListener("webglcontextrestored" , function(e){
        Simple.myerror("webglcontext restored");
        Simple.initLoop(canvas); 
    }, false);

	// Init and start Loop
	Simple.initLoop(canvas);
};



Simple.initLoop = function(canvas) 
{
    
    
	
    var para = {
        premultipliedAlpha : true,
//        alpha : false
    };
	var gl = Simple.getWebGLContext(canvas, para);
	if (!gl) {
        Simple.myerror("Failed to create WebGL context.");
        return;
    }

	
	gl.clearColor( 0.0 , 0.0 , 0.0 , 0.0 );	 

    
    
	
	Simple.loadBytes(modelDef.model, function(buf){
		live2DModel = Live2DModelWebGL.loadModel(buf);
	});

	
    var loadCount = 0;
	for(var i = 0; i < modelDef.textures.length; i++){
		(function ( tno ){
			loadedImages[tno] = new Image();
			loadedImages[tno].src = modelDef.textures[tno];
			loadedImages[tno].onload = function(){
				if((++loadCount) == modelDef.textures.length) {
                    loadLive2DCompleted = true;
                }
			}
			loadedImages[tno].onerror = function() { 
				Simple.myerror("Failed to load image : " + modelDef.textures[tno]); 
			}
		})( i );
	}
    
	
    
    (function tick() {
        Simple.draw(gl); 
        
        var requestAnimationFrame = 
            window.requestAnimationFrame || 
            window.mozRequestAnimationFrame ||
            window.webkitRequestAnimationFrame || 
            window.msRequestAnimationFrame;
		requestID = requestAnimationFrame( tick , canvas );
    })();
};


Simple.draw = function(gl)
{
	
	gl.clear(gl.COLOR_BUFFER_BIT);
    
	
	if( ! live2DModel || ! loadLive2DCompleted ) 
        return; 
	
	
	if( ! initLive2DCompleted ){
		initLive2DCompleted = true;

        
        for( var i = 0; i < loadedImages.length; i++ ){
            
            var texName = Simple.createTexture(gl, loadedImages[i]);
            
            live2DModel.setTexture(i, texName); 
        }

        
        loadedImages = null;

        
        live2DModel.setGL(gl);

        
        var s = 2.0 / live2DModel.getCanvasWidth(); 
        var matrix4x4 = [ s,0,0,0 , 0,-s,0,0 , 0,0,1,0 , -1.0,1,0,1 ];
        live2DModel.setMatrix(matrix4x4);
	}
    
	
    var t = UtSystem.getTimeMSec() * 0.001 * 2 * Math.PI; 
    var cycle = 3.0; 
    
    live2DModel.setParamFloat("PARAM_ANGLE_X", 30 * Math.sin(t/cycle));

    
    
    live2DModel.update(); 
    live2DModel.draw();	
};



Simple.getWebGLContext = function(canvas)
{
	var NAMES = [ "webgl" , "experimental-webgl" , "webkit-3d" , "moz-webgl"];
	
    var param = {
        alpha : true,
        premultipliedAlpha : true
    };
    
	for( var i = 0; i < NAMES.length; i++ ){
		try{
			var ctx = canvas.getContext( NAMES[i], param );
			if( ctx ) return ctx;
		} 
		catch(e){}
	}
	return null;
};



Simple.createTexture = function(gl, image/*WebGL Image*/) 
{
	var texture = gl.createTexture(); 
	if ( !texture ){
        mylog("Failed to generate gl texture name.");
        return -1;
    }
    
    gl.pixelStorei(gl.UNPACK_PREMULTIPLY_ALPHA_WEBGL, 1);
	gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, 1);	
	gl.activeTexture( gl.TEXTURE0 );
	gl.bindTexture( gl.TEXTURE_2D , texture );
	gl.texImage2D( gl.TEXTURE_2D , 0 , gl.RGBA , gl.RGBA , gl.UNSIGNED_BYTE , image);
    
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_NEAREST);
    
    
    gl.generateMipmap(gl.TEXTURE_2D);
    gl.bindTexture( gl.TEXTURE_2D , null );
    
	return texture;
};



Simple.loadBytes = function(path , callback)
{
	var request = new XMLHttpRequest();
	request.open("GET", path , true);
	request.responseType = "arraybuffer";
	request.onload = function(){
		switch( request.status ){
		case 200:
			callback( request.response );
			break;
		default:
			Simple.myerror( "Failed to load (" + request.status + ") : " + path );
			break;
		}
	}
    
    request.send(null); 
};



Simple.mylog = function(msg/*string*/)
{
	var myconsole = document.getElementById("myconsole");
	myconsole.innerHTML = myconsole.innerHTML + "<br>" + msg;
	console.log(msg);
};


Simple.myerror = function(msg/*string*/)
{
    console.error(msg);
	Simple.mylog( "<span style='color:red'>" + msg + "</span>");
};