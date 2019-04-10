var mino={};
var _mino_layer_smooth_obj=null;
var _mino_layer_smooth_doing=false;
mino.layer={	xy:
      function(obj) {
    		if(document.getBoxObjectFor) { 
      		//firefox			
      		try {
      			var tempXY=document.getBoxObjectFor(obj);
      		} catch(e) {
      		  return [0,0,obj.offsetWidth,obj.offsetHeight]
      		}			
      		return [tempXY.x,tempXY.y,obj.offsetWidth,obj.offsetHeight];		
    		} else if(obj.getBoundingClientRect) { 
    		  //internet Explorer
    		  var tempXY=obj.getBoundingClientRect();
    		  return [(tempXY.left+document.body.scrollLeft),(tempXY.top+document.body.scrollTop),obj.offsetWidth,obj.offsetHeight];		
    		} else 
    		  return false;
      },
    moveSmooth:
      function(obj,x,y) {		
        if(!_mino_layer_smooth_obj) _mino_layer_smooth_obj=obj;
        if(arguments[3]==undefined && _mino_layer_smooth_doing) {
          return;
        }	else 
          _mino_layer_smooth_doing=1;		
        var loc=mino.layer.xy(_mino_layer_smooth_obj);
        if(x!=null) {
          if(Math.abs(Math.abs(loc[0])-Math.abs(x))<10) {
            obj.style.left=x;
            var xc=1;
          } else {
            obj.style.left=parseInt(loc[0]+(x-loc[0])/3);
          }
        } else 
          var xc=1;		
        if(y!=null) {
          if(Math.abs(Math.abs(loc[1])-Math.abs(y))<10) {
            obj.style.top=y;
            var yc=1;
          } else {
            obj.style.top=parseInt(loc[1]+(y-loc[1])/3);
          }
        } else 
          var yc=1;
          if(xc && yc) {
            _mino_layer_smooth_doing=0;
            _mino_layer_smooth_obj=null;
            return;
        }
        setTimeout('mino.layer.moveSmooth(_mino_layer_smooth_obj,'+x+','+y+',"s")',10);	
      }
}