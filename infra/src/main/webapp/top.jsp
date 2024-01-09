<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<% String url = application.getContextPath() + "/"; %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
	<title>상품 응모 사이트</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- css 외부 파일을 inclue-->
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


	<!-- 현 html에 css로 꾸미는 것을 명시-->
	<style>
		body,
		h1,
		h2,
		h3,
		h4,
		h5,
		h6 {
			font-family: "Karma", sans-serif
		}

		.w3-bar-block .w3-bar-item {
			padding: 20px
		}
	</style>


</head>

<body>

	<!-- Sidebar -->
	<nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left"
		style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
		<a href=pants.jsp onclick="w3_close()" class="w3-bar-item w3-button">하의</a>
		<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button">상의</a>
		<a href=shose.jsp onclick="w3_close()" class="w3-bar-item w3-button">신발</a>
	<a href=product.html onclick="w3_close()" class="w3-bar-item w3-button">상품 저장 페이지</a>
	<a href=allpage.jsp onclick="w3_close()" class="w3-bar-item w3-button">전체 상품 조회 페이지</a>
	</nav>

	<!-- Top menu -->
	<div class="w3-top">
		<div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
			<div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">&#9776;</div>
			<div class="w3-center w3-padding-16"><img src="images/brand_logo.jpg" alt="Steak" style="width:100px;height:70px"></div>
		</div>
	</div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">

		<%
		//로그인된 아이디가 있는지 읽어와보기
		Long customerId =(Long)session.getAttribute("customerId");
		String name =(String)session.getAttribute("name");
		%>
		
		<%if(customerId==null){%>
			<a href="login.html" target="_self">
				<button style="font-size:15px">로그인</button>
			</a>
		<%}else{ %>	
			<%= name %> 님 환영합니다.
		<%} %>

		<!-- First Photo Grid-->
		<div class="w3-row-padding w3-padding-16 w3-center" id="food">
			<div class="w3-quarter">
				<img src="images/top1.png" alt="Sandwich" style="width:100%">
				<h4>Barbour Annandale Quilted Jacket Black</h4>
				<p>가격 228,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(1)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top2.png" alt="Steak" style="width:100%">
				<h4>Isabel Marant Moby Sweatshirt Ecru Black</h4>
				<p>가격 320,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(2)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top3.PNG" alt="Cherries" style="width:100%">
				<h4>Moncler Padded Wool Cardigan Black - 23FW</h4>
				<p>가격 1,166,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(3)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top4.PNG" alt="Pasta and Wine" style="width:100%">
				<h4>Play Comme des Garcons Black Heart Knit Cardigan Grey</h4>
				<p>가격 300,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(4)" style="font-size:10px">응모하기</button>
				</a>
			</div>
		</div>

		<!-- Second Photo Grid-->
		<div class="w3-row-padding w3-padding-16 w3-center">
			<div class="w3-quarter">
				<img src="images/top5.PNG" alt="Popsicle" style="width:100%">
				<h4>Stussy Basic Stussy Hoodie Black 2023</h4>
				<p>가격 207,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(5)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top6.png" alt="Salmon" style="width:100%">
				<h4>The North Face White Label Novelty Nuptse Down Jacket Ice Gray</h4>
				<p>가격 280,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(6)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top7.PNG" alt="Sandwich" style="width:100%">
				<h4>Thom Browne Donegal Twist Cable 4-Bar Classic Crewneck Pullover Light Grey</h4>
				<p>가격 510,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(7)" style="font-size:10px">응모하기</button>
				</a>
			</div>
			<div class="w3-quarter">
				<img src="images/top8.png" alt="Salmon" style="width:100%">
				<h4>Vivienne Westwood Bea Top Black</h4>
				<p>가격 257,000원</p>
				<!-- 버튼 추가 -->
				<a href="#pant1" id="btn">
					<button onclick="drawView(8)" style="font-size:10px">응모하기</button>
				</a>
			</div>
		</div>

		<!-- Pagination -->
		<div class="w3-center w3-padding-32">
			<div class="w3-bar">
				<a href="#" class="w3-bar-item w3-button w3-hover-black">&laquo;</a>
				<a href="#" class="w3-bar-item w3-black w3-button">1</a>
				<a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
				<a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
				<a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
				<a href="#" class="w3-bar-item w3-button w3-hover-black">&raquo;</a>
			</div>
		</div>
	</div>

	<script>
		// Script to open and close sidebar
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}

		function drawView(v){	
			var xhttp = new XMLHttpRequest();
			xhttp.open( "get", "Draws?productId="+v);
			xhttp.send();
		}	
	</script>

</body>
</html>