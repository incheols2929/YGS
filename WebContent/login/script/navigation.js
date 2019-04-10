var imgPlaces= 0;

function getObj( id ) {
	return document.getElementById( id );
}


dir=0; // 0 = left, 1 = right
index = 0;
timerNavI = 0;
click=0;

// **** Functional Code - NO NEED to Change
function zxcBAnimator(zxcmde,zxcobj,zxcsrt,zxcfin,zxctime){
 if (typeof(zxcobj)=='string'){ zxcobj=document.getElementById(zxcobj); }
 if (!zxcobj||(!zxcsrt&&!zxcfin)||zxcsrt==zxcfin) return;
 var zxcoop=zxcobj[zxcmde.replace(/[-#]/g,'')+'oop'];
 if (zxcoop){
  clearTimeout(zxcoop.to); 
  if (zxcoop.srtfin[0]==zxcsrt&&zxcoop.srtfin[1]==zxcfin&&zxcmde.match('#')) zxcoop.update([zxcoop.data[0],(zxcoop.srtfin[0]==zxcoop.data[2])?zxcfin:zxcsrt],zxctime);
  else zxcoop.update([zxcsrt,zxcfin],zxctime);
 }
 else zxcobj[zxcmde.replace(/[-#]/g,'')+'oop']=new zxcBAnimatorOOP(zxcmde,zxcobj,zxcsrt,zxcfin,zxctime);
}

function zxcBAnimatorOOP(zxcmde,zxcobj,zxcsrt,zxcfin,zxctime){
 this.srtfin=[zxcsrt,zxcfin];
 this.to=null;
 this.obj=zxcobj;
 this.mde=zxcmde.replace(/[-#]/g,'');
 this.update([zxcsrt,zxcfin],zxctime); 
}

zxcBAnimatorOOP.prototype.update=function(zxcsrtfin,zxctime){
 this.time=zxctime||this.time||2000;
 this.data=[zxcsrtfin[0],zxcsrtfin[0],zxcsrtfin[1]];
 this.srttime=new Date().getTime();
 this.cng();
}

zxcBAnimatorOOP.prototype.cng=function(){
 var zxcms=new Date().getTime()-this.srttime;
 //getObj('info').innerHTML = this.data[2]+"|"+this.data[1]+"|"+zxcms+'<'+this.time; 
 
 this.data[0]=(this.data[2]-this.data[1])/this.time*zxcms+this.data[1];
 
 if (this.mde!='left'&&this.mde!='top'&&this.data[0]<0) this.data[0]=0;
 if (this.mde!='opacity') this.obj.style[this.mde]=this.data[0]+'px';
 else  zxcOpacity(this.obj,this.data[0]);
 if (zxcms<this.time) this.to=setTimeout(function(zxcoop){return function(){zxcoop.cng();}}(this),10);
 else {  
 	if (this.mde=='left') {
  		this.data[0]=this.data[2];
  		timerNavI = 0;    		
  		if(dir==1) {
  			setHide(index+3); 
  			setHide(index+4);
  			setHide(index+5);
  		}
  	}
  	if (this.mde!='opacity') this.obj.style[this.mde]=this.data[0]+'px';
  	else {
  		zxcOpacity(this.obj,this.data[0]);
  		if( this.data[0] <= 0 && this.data[2]<=0 ) getObj( 'div_'+hideItem+'_'+hideSubItem ).style.display = '';	
  	}
 }
}

function zxcOpacity(zxcobj,zxcopc){
 if (zxcopc<0||zxcopc>100) return;
 zxcobj.style.filter='alpha(opacity='+zxcopc+')';
 zxcobj.style.opacity=zxcobj.style.MozOpacity=zxcobj.style.KhtmlOpacity=zxcopc/100-.001;
}
// end

function setVisible( id ) {
	currentDiv=getObj( 'div_'+id);
	if( currentDiv != null )
	if( currentDiv.style.display == 'none' ) currentDiv.style.display = 'block';	
}

function setHide( id ) {
	currentDiv=getObj( 'div_'+id);	
	if( currentDiv != null )
	if( currentDiv.style.display != 'none' ) currentDiv.style.display = 'none';			
}

function goPrev(dirIMG) {		
	dir = 1;
	if( index >= 1 && !timerNavI ) {
		timerNavI = 1;		
		//start = -(index*195)+51;
		//end   =  ((3-index)*195)+51;
		start = -(index*81);
		end   =  ((5-index)*81);
		index = index -5;	
		
		//alert((index - 5));
		zxcBAnimator('left#','sourceDiv',start,end,200);
		getObj('imgNext').src = 'img/right_btn_1.gif';
		
		if( index == 0 ) {
			getObj('imgPrev').src = 'img/left_btn_2.gif';		
			//getObj('btnLeftDiv').className = 'btnLeft colorE';
			//getObj( 'carBotLeft' ).className = 'pBotLeftCar';
		}
	}	
}

function goNext(dirIMG) {	
	dir = 0;	
	if(( index + 2 ) < imgPlaces && !timerNavI ) {
		timerNavI = 1;		
		setVisible( index + 3 );
		setVisible( index + 4 );
		setVisible( index + 5 );
		//start = -(index*195)+51;
		//end   =  (-(index+3)*195)+51;
		start = -(index * 81);
		end   =  (-(index+5) * 81);
		index = index + 5;			
		//alert((index + 5));
		zxcBAnimator('left#','sourceDiv',start,end,200);
		getObj('imgPrev').src = 'img/left_btn_1.gif';
		
		if( index + 2 >= imgPlaces ) {
			getObj('imgNext').src = 'img/right_btn_2.gif';		
			//getObj('btnRightDiv').className = 'btnRight colorE';
			//getObj( 'carBotRight' ).className = 'pBotRightCar';
		}
	}
}

/*function onMove( id ) { 
	if( index >= 1 && !timerNavI ) {
		if( id == 'btnLeftDiv' ) getObj( id ).className = 'btnLeft colorS';
		if( id == 'btnLeftDiv' ) getObj( 'carBotLeft' ).className = 'pBotLeftCarSel';
	}
	
	if(( index + 2 ) < imgPlaces && !timerNavI ) {
		if( id == 'btnRightDiv' ) getObj( id ).className = 'btnRight colorS';
		if( id == 'btnRightDiv' ) getObj( 'carBotRight' ).className = 'pBotRightCarSel';
	}
}

function onOut( id ) { 
	if( id == 'btnLeftDiv' ) getObj( id ).className = 'btnLeft colorE';
	if( id == 'btnLeftDiv' ) getObj( 'carBotLeft' ).className = 'pBotLeftCar';
	
	if( id == 'btnRightDiv' ) getObj( id ).className = 'btnRight colorE';
	if( id == 'btnRightDiv' ) getObj( 'carBotRight' ).className = 'pBotRightCar';
	
}*/

function itemMove( id ) {	
	getObj( 'divItem_'+id ).className = 'selectItem';		
}

function itemOut( id ) {
	getObj( 'divItem_'+id ).className = '';
}

function initButtons() {
	if( index + 2 >= imgPlaces ) {
		getObj('imgNext').src = 'img/right_btn_1.gif';		
	}else{
		getObj('imgNext').src = 'img/right_btn_2.gif';
	}
	if( index == 0 ) {
		getObj('imgPrev').src = 'img/left_btn_2.gif';		
	}
}