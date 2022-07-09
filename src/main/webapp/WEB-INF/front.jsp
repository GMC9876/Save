<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />

        <script>
			function logout() {
				if (!confirm('정말로 로그아웃 하시겠습니까?')) {
					alert("취소했습니다");
					return false;
				}
				var b = eval(true)
				$.ajax({
					url:'/logout',
					method:'get',
					cache : false,
					dataType : 'text',
					success : function(eval) {
						alert(b ? '로그아웃 성공' : '로그아웃 실패');
						location.href = '/login';
					},
					error : function(xhr, status, err) {
						alert('에러:' + err);
					}
				});
				return false;
			}
		</script>
		
    </head>
    <body class="sb-nav-fixed" >
		<jsp:include page="/WEB-INF/topnav.jsp"></jsp:include>
        <div id="layoutSidenav">
			<jsp:include page="/WEB-INF/sidenav.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-0">
                        
                        <img src="/assets/img/qqqqqq.png" class="img-responsive" height="90%" width="100%"/>
                        </div>
                </main>
                <jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
