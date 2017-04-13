<html>
<head>
</head>
<body>
<div style="margin-top: -250px;">
	<br />
	<form action="login.html" id="search">
		  <input type="checkbox" name="open" id="openId"/> <label for="open">Open</label> &nbsp;&nbsp;&nbsp;
		  <input type="checkbox" name="close" id="closeId"/> <label for="closed">Closed</label> &nbsp;&nbsp;&nbsp;
		  Title : <input type="text" name="title" id="titleId" /> 
		  <input type="button" value="Search" onclick="searchPostedJobs()" />
	</form>
	<br/><br/><br/>
	 <div id="jobsId" style="position: absolute; margin-left:20px "></div>
	 <div id="shortListedId" style="margin-left: 400px"></div>
	 <div id="interViewId" style="margin-left: 700px"></div>
</div>
	<script type="text/javascript">
    var contexPath = "<%=request.getContextPath() %>";
    function searchPostedJobs(){
    	jQuery.ajax({
            type: "POST",
            url: contexPath + "/searchPostedJobs.html",
            data: $("#search").serialize(),
            success: function(response){ 
            	$("#jobsId").html(response);
            }
    	}) ;
    }
    function getShortlistedCandidate(com, title){
		jQuery.ajax({
            type: "POST",
            url: contexPath + "/getShortlistedCandidate.html",
            data: "company= "+com +"&title="+title,
            success: function(response){ 
            	$("#shortListedId").html(response);
            }
    	}) ;
	}
    
    function getInterviewResults(name, worksAt){
    	jQuery.ajax({
            type: "POST",
            url: contexPath + "/getInterviewResults.html",
            data: "name= "+name +"&worksAt="+worksAt+"&title="+$("#titleId").val(),
            success: function(response){ 
            	$("#interViewId").html(response);
            }
    	}) ;
    }
    </script>
</body>
</html>