<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.marconfus.dino.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>

<link rel="stylesheet"
	href="lib/jquery.mobile-1.2.0.min.css" />
<link rel="stylesheet" href="my.css" />

<style>
/* App custom styles */
</style>

<script src="lib/jquery.min.js"></script>
<script src="lib/jquery.mobile-1.2.0.min.js"></script>
<script src="my.js"></script>
</head>

<body>
	<!-- Home -->
	<div data-role="page" id="stationselection">

		<div data-theme="a" data-role="header">
			<h2>Select player</h2>
		</div>
	
		<div data-role="content">	
			<ul data-role="listview" data-divider-theme="b" data-inset="true">
				<%
					for (PlayerDevice pd : PlayerDeviceDb.getInstance().getPlayers()) {
				%>
				<li data-theme="c"><a href="#" data-rel="back"
					onclick="station('<%=pd.getID()%>')"><%=pd.getName()%></a></li>
				<%
					}
				%>
			</ul>
		</div>
	
	<div data-theme="a" data-role="footer" data-position="fixed">
		<h3>
			<!--  Footer -->
		</h3>
	</div>
	
	</div>
</body>
</html>
