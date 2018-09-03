<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>使用Ajxa实现搜索自动提醒功能</title>
</head>
<body>
	<p>请输入要搜索的用户名：</p>
	<input id="search_input" type="text" style="width:300px;"  onkeyup="test(this.value,event)" />  
	<select multiple="multiple" id="sel" onchange="test2()" style="width:300px;display:none;color:gray"></select>
</body>
</html>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function test(keyword,event){    
    //定义全局变量         
    var sel = document.getElementById("sel"); 
    $.ajax({
        url : "/SSM/AjaxController/ajax.do",
        type : "GET",
        dataType:"json",
        contentType : "application/json;charset=UTF-8",
        //<!-- 向后端传输的数据 -->
        data :{
        	username: $("#search_input").val(),
        },
        success:function(result) {
            //<!-- 处理后端返回的数据 -->
            for(var i=0;i<result.length;i++){  
                //将当前循环满足条件的商品名称生成一个下拉的选项    
               sel.options[i]=new Option(result[i].username,i);
            }
            //自动设置高度  
            sel.size=result.length;
            //判断是否有满足条件的商品    
            if(result.length>0){    
                sel.style.display='block';    
            }else{    
                sel.style.display='none';    
            }
        },
        error:function(result){
        	alert("error");
        }
    });  
  //当用户按下上下键时获取相应的值    
    if(event.keyCode==40){ 
        sel.focus(); 
    }
   
}; 
            
     function test2(){
         //将选中的下拉列表中的内容添加到输入框中    
         $("#search_input").val($("option:selected").text()); 
            //输入回车，获取输入框内容焦点    
            $("#sel").keypress(function(){   
                    $("#search_input").focus();   
                    $("#search_input").attr("onkeyup",""); 
                    $("#sel").hide();   
                    
            });   
             //双击，获取输入框内容焦点    
             $("#sel").click(function(){    
                 $("#search_input").focus();    
                 $("#sel").css("display","none");  
                /* var keyword=$("#search_input").val(); 
                 location.href="/blog/user/search?searchid="+keyword;*/  
             });    
 
              
    }
</script>