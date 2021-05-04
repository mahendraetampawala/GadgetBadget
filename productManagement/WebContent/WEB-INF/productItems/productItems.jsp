<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
</head>
<body>
	<header>
		<div class="span8">
			<div class="account pull-right">
				<ul class="user-menu-top-nav">
					<li><a href="#">My account</a></li>
					<li><a href="#">Cart</a></li>
				</ul>
			</div>
		</div>
		<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index.html" class="logo pull-left"><img src="themes/images//logo.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="./products.html">Woman</a>					
								<ul>
									<li><a href="./products.html">Lacinia nibh</a></li>									
									<li><a href="./products.html">Eget molestie</a></li>
									<li><a href="./products.html">Varius purus</a></li>									
								</ul>
							</li>															
							<li><a href="./products.html">Man</a></li>			
							<li><a href="./products.html">Sport</a>
								<ul>									
									<li><a href="./products.html">Gifts and Tech</a></li>
									<li><a href="./products.html">Ties and Hats</a></li>
									<li><a href="./products.html">Cold Weather</a></li>
								</ul>
							</li>							
							<li><a href="./products.html">Hangbag</a></li>
							<li><a href="./products.html">Best Seller</a></li>
							<li><a href="./products.html">Top Seller</a></li>
						</ul>
					</nav>
				</div>
			</section>			
	</header>
	
	<div class="span5">					
						<h4 class="title"><span class="text"><strong>Add Items</strong> Form</span></h4>
						<form action="<%= request.getContextPath() %>/Items" method="post">
							<input type="hidden" name="next" value="/">
							<fieldset>
								<div class="control-group">
									<label class="control-label">Item Code</label>
									<div class="controls">
										<input type="text" placeholder="Enter your Itemcode" id="Itemcode" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Item Name</label>
									<div class="controls">
										<input type="text" placeholder="Enter your Itemname" id="Itemname" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<input tabindex="3" class="btn btn-inverse large" type="submit" value="additem">
									
								</div>
							</fieldset>
						</form>				
					</div>
	
	
	
	
	<footer>
	
	</footer>
</body>
</html>