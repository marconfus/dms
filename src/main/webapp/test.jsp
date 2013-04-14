<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.marconfus.dino.model.*" %>
<html>
<body>
<h2>Available Players</h2>

<ul>
<% for (PlayerDevice player : PlayerDeviceDb.getInstance().getPlayers()) {%>
	<li><a href="play/<%= player.getID() %>"><%= player.getName() %></a></li>
<% } %>
</ul>

<ul>
<% for (RadioStation station : RadioStationDb.getInstance().getStations()) {%>
	<li><%= station.getName() %></li>
<% } %>
</ul>

</body>
</html>
