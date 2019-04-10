if(!window._N) _N = {};

_N.info = {
		start : 0,  
		end : 0,
		pageSize:10,   //리스트에 보여질 글의 수
		pageBlock :10,  //한페지에 보여질 블럭의 수
		pageCount : 0,   //전체 블럭의 수 계산
		startPage : 0,   //시작블럭
		endPage :0,  //끝블럭
		number:0,   // 인덱스계산을 위한 변수
		count:0,   //db에 있는 전체 데이터의 수
		currentPage:0,
		pageNum: 0
		
};
_N.util ={
		formatYMD : function(ymd){
			var length = ymd.length;
			
			if(length == 4){
				return ymd + "년";
			}else if(length == 6){
				var str = ymd.substring(0, 4) + "년 ";
				str += ymd.substring(4) + "월";
				return str;
			}else if(length == 8){
				var str = ymd.substring(0, 4) + "년 ";
				str += ymd.substring(4, 6) + "월 ";
				str += ymd.substring(6, 8) + "일";
				return str;
			}else if(length == 10){
				var str = ymd.substring(0, 4) + "년 ";
				str += ymd.substring(4, 6) + "월 ";
				str += ymd.substring(6, 8) + "일 ";
				str += ymd.substring(8) + "시";
				return str;
			}
		}
};
_N.init = {
		//공지사항 페이징처리
		paging : function(pagenum, changenum){
		
			if(changenum == "1"){
				_N.info.pageSize = 10;
			}else if(changenum == "2"){
				_N.info.pageSize = 10;
			}
	
			$.ajax({
				type:"POST",
				url:"./XML/noticecnt.jsp",
				dataType : "xml",
				success: function(xml){
					
					if (_N.info.pageNum == 0){ 
						_N.info.pageNum = "1"; 
			    	}else{
			    		_N.info.pageNum = pagenum;
				    }
					if($(xml).find("data").length != 0){
						_N.info.currentPage = Number(_N.info.pageNum); 
						_N.info.start = (_N.info.currentPage - 1) * _N.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_N.info.end = _N.info.start + _N.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_N.info.count = $(xml).find("data").find("tot").text();
						_N.info.number = _N.info.count - (_N.info.currentPage - 1) * _N.info.pageSize;  //인덱스 값 구하기
						
						if(changenum == "1"){
							_N.init.noticeLoding(_N.info.start, _N.info.pageSize);
						}else if(changenum == "2"){
							_N.init.noticeListView(_N.info.start, _N.info.pageSize);
						}
						
					}
				},
				error: function(xhr, status, error) {
						//alert("에러");
				}
			});	
		},
		//화면의 공지사항
		noticeLoding : function(start, pageSize){
		
			$.ajax({
				type:"POST",
				url:"./XML/noticelist.jsp",
				dataType : "xml",
				data : {stnum:start, pagenum:pageSize},
				success: function(xml){	
					
					if($(xml).find("notice").length != 0){
						var str = "";
						var list_tem = "";
						str+="<table border='0' cellpadding='0' cellspacing='0' width='100%' class='noticeTable'>";
						
						$(xml).find("notice").each(function(idx, item){
							var seq  = $(this).find("seq").text();
							var subject = $(this).find("subject").text();
							var category = $(this).find("category").text();
							var reg_date = $(this).find("reg_date").text();
							var reg_user = $(this).find("reguser").text();
							if(category == "1"){
								list_tem = "[ 공지사항 ]&nbsp;";
								list_tem = "";
							}

							str+="<tr>";
							str+="	<td height='20' align='center' width='5%'><img src='img/p.gif'/></td>";
							str+="	<td width='60%'>"+list_tem + "<a href='#' onclick='_N.init.noticeView(" + seq + ");'>"+subject+"</a></td>";
							str+="	<td align='center'>"+reg_user+"</td>";
							str+="	<td align='center'>"+_N.util.formatYMD(reg_date)+"</td>";
							str+="</tr>";

						});
						str+="</table>";
						
						$("#notice_box").html(str);
						
					}else{
						var str = "<div style='border:2px solid #009e96; background-color:#f3f5f9; height:30px; margin:55px 0 0 0; text-align:center;'>";
						str += "<p style='padding:8px;'>공지사항목록이 존재하지않습니다.</p>"; ///화면에 나오는 공지사
						str += "</div>";
						
						$("#notice_box").html(str);
					}
				},
				error: function(xhr, status, error) {
						//alert("에러");
				},
				beforeSend : function(){ //요청이 전송되기에 앞서 호출
					//$('#viewLayer').showLoading();
				},
				complete : function(){ //요청 완료시
					//$('#viewLayer').hideLoading();
				}
			});
		},
		//팝업 화면의 공지사항 리스트
		noticeListView : function(start, pageSize){

			$.ajax({
				type:"POST",
				url:"./XML/noticelist.jsp",
				dataType : "xml",
				data : {stnum:start, pagenum:pageSize},
				success: function(xml){	
					if($(xml).find("notice").length != 0){
						var str = "";
						var list_tem = "";
						var pstr = "";
						
						str+="<table border='0' cellpadding='0' cellspacing='0' height='auto' width='100%'>";
						str+="<tr style='background: url(img/notice_full_bg.gif) repeat-x;' align='center'>";
						str+="<td height='28' width='10%'><b>번호</b></td>";
						str+="<td width='60%'><b>제목</b></td>";
						str+="<td width='40%'><b>날짜</b></td>";
						str+="</tr>";
						
						$(xml).find("notice").each(function(idx, item){
							
							var seq  = $(this).find("seq").text();
							var reguser  = $(this).find("reguser").text();
							var subject  = $(this).find("subject").text();
							var content  = $(this).find("content").text();
							var category  = $(this).find("category").text();
							var reg_date  = $(this).find("reg_date").text();
							var read_count  = $(this).find("read_count").text();
							
							str+="<tr align='center'>";
							str+="<td height='25' width='10%'  class='notice_dot'>" + _N.info.number-- + "</td>";
							str+="<td width='60%' align='left' class='notice_dot'><a href='#' onclick='_N.init.noticeView(" + seq + ");' style='text-decoration: none;'>" + subject + "</td>";
							str+="<td width='40%' class='notice_dot'>" + reg_date + "</td>";
							str+="</tr>";
							
						});
						str+="</table>";
						
						$("#inner_middle").html(str);
						$("#inner_bottom").html(_N.init.bottompageing(2));
						//팝업을연다.
						$('#pop_notice').bPopup({
							fadeSpeed:'slow', 
							modalColor:'gray', 
							scrollBar:false,
							opacity:0.3	
						});
						
					}else{
						alert("공지사항 목록이 존재하지 않습니다.");
					}
				},
				error: function(xhr, status, error) {
						//alert("에러");
				},
				beforeSend : function(){ //요청이 전송되기에 앞서 호출
					//$('#viewLayer').showLoading();
				},
				complete : function(){ //요청 완료시
					//$('#viewLayer').hideLoading();
				}
			});
		},
		
		noticeView : function(objk){
		
			$.ajax({
				type:"POST",
				url:"./XML/noticeview.jsp",
				dataType : "xml",
				data : {key:objk},
				success: function(xml){	
					var str="";
					if($(xml).find("notice").length != 0){
					
						var notice = $(xml).find("notice"); 
						var seq  = notice.find("seq").text();
						var reguser  = notice.find("reguser").text();
						var subject  = notice.find("subject").text();
						var content  = notice.find("content").text();
						var category  = notice.find("category").text();
						var reg_date  = notice.find("reg_date").text();
						var read_count  = notice.find("read_count").text();
						
						str+="<table border='0' cellpadding='0' cellspacing='0' height='auto' width='100%' style='border-top:2px solid #285cba;'>";
						str+="<tr align='center'>";
						str+="<td height='28' width='10%' class='border_line1' bgcolor='#c0d4f6'><b>제목</b></td>";
						str+="<td width='60%' class='border_line1' align='left'>&nbsp;" + subject + "</td>";
						str+="<td width='10%' class='border_line2'></td>";
						str+="</tr>";
						str+="<tr align='center'>";
						str+="<td width='10%' class='border_line1' bgcolor='#c0d4f6'><b>내용</b></td>";
						str+="<td height='289' width='90%' colspan='3' align='left' class='border_line2'>";
						str+="<div style='height:100%; overflow:auto; padding:3px;'>";
						str+="<p style='padding:5px; line-height:18px;'>";
						str+= content;
						str+="</p>";
						str+="</div>";
						str+="</td>";
						str+="</tr>";
						str+="<tr align='center'>";
						str+="<td height='28' width='10%' class='border_line1' bgcolor='#c0d4f6'><b>등록일</b></td>";
						str+="<td width='60%' class='border_line2' align='left'>&nbsp;" + reg_date + "</td>";
						str+="<td width='20%' class='border_line2' colspan='2'><a href='#' style='text-decoration: none;' onclick='_N.init.paging(1, 2);'><b>[목록]</b></a></td>";
						str+="</tr>";
						str+="</table>";
						
						$("#inner_middle").html(str);
						$("#inner_bottom").html("");
						
						$('#pop_notice').bPopup({
							fadeSpeed:'slow', 
							modalColor:'gray', 
							scrollBar:false,
							opacity:0.3	
						});
						
					}else{
						alert("목록이 존재하지 않습니다.");
					}
					
					
				},
				error: function(xhr, status, error) {
						//alert("에러");
				},
				beforeSend : function(){ //요청이 전송되기에 앞서 호출
					//$('#viewLayer').showLoading();
				},
				complete : function(){ //요청 완료시
					//$('#viewLayer').hideLoading();
				}
			});
			
		},
		
		//화면의 페이지처리
		bottompageing : function(bbsnum){
			
			var pstr="";
			
			_N.info.pageCount = Math.floor((_N.info.count / _N.info.pageSize) + (_N.info.count % _N.info.pageSize == 0 ? 0 : 1));
			_N.info.startPage = Math.floor(_N.info.currentPage - ((_N.info.currentPage-1)%_N.info.pageBlock));
			_N.info.endPage = Math.floor(_N.info.startPage + _N.info.pageBlock - 1); //끝 페이지 구하기
			if(_N.info.endPage > _N.info.pageCount)_N.info.endPage = _N.info.pageCount;
			
				pstr += "<p style='padding-top:8px;'>";
					if (_N.info.startPage > _N.info.pageBlock) {
						pstr += " <img src='./img/prev_1.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_N.init.paging(\""+(_N.info.startPage - _N.info.pageBlock)+"\",\""+bbsnum+"\");'/> ";
						pstr += " <img src='./img/prev_2.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_N.init.paging(\""+(_N.info.currentPage-1)+"\",\""+bbsnum+"\");'/> "; 
					}else{
						pstr += " <img src='./img/prev_1_off.gif' border='0' align='absmiddle'/> ";
						pstr += " <img src='./img/prev_2_off.gif' border='0' align='absmiddle'/> ";
					}

					for (i = _N.info.startPage; i <= _N.info.endPage; i++) {
						if(i == _N.info.currentPage){
							pstr += " <a href='javascript:_N.init.paging(\""+i+"\",\""+bbsnum+"\")' style='text-decoration: none;'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_N.init.paging(\""+i+"\",\""+bbsnum+"\")' style='text-decoration: none;'>" + i + "</a> ";
						}
						if(i != _N.info.endPage){
							pstr += " | ";
						}
					}
					if (_N.info.endPage < _N.info.pageCount) {
						pstr += " <img src='./img/next_2.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_N.init.paging(\""+(_N.info.currentPage+1)+"\",\""+bbsnum+"\");'/> ";
						pstr += " <img src='./img/next_1.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_N.init.paging(\""+(_N.info.startPage + _N.info.pageBlock)+"\",\""+bbsnum+"\");'/> ";
					}else{
						pstr += " <img src='./img/next_2_off.gif' border='0' align='absmiddle'/> ";
						pstr += " <img src='./img/next_1_off.gif' border='0' align='absmiddle'/> ";
					}
				pstr += "</p>";
				
				return pstr;
		}

}