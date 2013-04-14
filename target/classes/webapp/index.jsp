<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.marconfus.dino.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>DinoMusicServer</title>

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
			<h2>Dino Radio Server</h2>

			<div class="ui-grid-b">

				<div class="ui-block-a">
					<a data-role="button" href="playerselection.jsp" data-rel="dialog"
						data-transition="pop" data-icon="grid" data-iconpos="left"> 
						<% PlayerDevice p = AppConfig.getInstance().getSelectedPlayer(); if (p != null) {
 							%><%=p.getName()%> 
 						<% } else { %>
 							Player
 						<%	} %>
					</a>
				</div>

				<div class="ui-block-b">
					<a data-role="button" href="#preferences" data-icon="gear"
						data-iconpos="left"> Preferences </a>
				</div>
				
				<div class="ui-block-c">
					<a data-role="button" href="#" data-icon="delete"
						data-iconpos="left" onclick="stop()"> Stop </a>
				</div>
				
			</div>
			
			<div data-role="content">
				<ul data-role="listview" data-divider-theme="b" data-inset="true">
					<li data-role="list-divider" role="heading">Radio Stations</li>

					<%
						for (RadioStation rs : RadioStationDb.getInstance().getStations()) {
					%>
					<li data-theme="c"><a href="#"
						onclick="play('<%=rs.getID()%>')"><%=rs.getName()%> </a></li>
					<%
						}
					%>

				</ul>
				This is another text.
			</div>
		</div>
		<div data-theme="a" data-role="footer" data-position="fixed">
			This is the footer.
		</div>
	</div>
	<script>
            function play(id){
            	$.ajax('rest/play/' + id);
            }
            function stop(){
            	$.ajax('rest/stop');
            }
            
            function station(id){
            	$.ajax('rest/selectplayer/' + id);
			}
	</script>
</body>
</html>
