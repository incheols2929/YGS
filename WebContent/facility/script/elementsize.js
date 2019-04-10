
if(!window._S) _S = {};

_S.utils = {
	ele : function(obj){
		return (typeof(obj) == "string")? document.getElementById(obj) : obj;
	},
	autoParentHeight : function(_parent, _child, offsetTop) {
		_S.utils.setParentHeight(_parent,_child,offsetTop);
		//setTimeout(function(){_S.utils.setParentHeight(_parent,_child,offsetTop)},50);
	},
	setParentHeight : function(_parent,_child,offsetTop) {
		
		
		var childElement = this.ele(_child);
		var parentElement = this.ele(_parent);
		
		var size = Number(parentElement.offsetHeight) - Number(offsetTop);
		if(size > 0){
			childElement.style.height = size+"px";
			
		}else{
			childElement.style.height = 0+"px";
			
		}
		
	},
	autoParentWidth : function(_parent, _child, offsetLeft) {
		_S.utils.setParentWidth(_parent,_child,offsetLeft);
		//setTimeout(function(){_S.utils.setParentWidth(_parent,_child,offsetLeft)},50);
	},
	setParentWidth : function(_parent,_child,offsetLeft) {
		
		
		var childElement = this.ele(_child);
		var parentElement = this.ele(_parent);
		
		var size = Number(parentElement.offsetWidth) - Number(offsetLeft);
		if(size > 0){
			childElement.style.width = size+"px";
			
		}else{
			childElement.style.width = 0+"px";
			
		}
			
	},
	getParentHeight : function(_parent,offsetTop) {
		
		
		
		var parentElement = this.ele(_parent);
		
		var size = Number(parentElement.offsetHeight) - Number(offsetTop);
		if(size > 0){
			
			return size;
		}else{
			
			return 0;
		}
		
	},
	getParentWidth : function(_parent,offsetLeft) {
		
		
		var parentElement = this.ele(_parent);
		
		var size = Number(parentElement.offsetWidth) - Number(offsetLeft);
		if(size > 0){
			return size;
		}else{
			return 0;
		}
			
	}
	
}