<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
<h3>Posted Jobs</h3> <br/>
	<table style="width: auto;" >
		<thead>
		 <tr>
			<th>Company</th>
			<th>Title</th>
			<th>Date Posted</th>
			<th>Status</th>
		 </tr>
		</thead>
		
		<c:set var="count" value="0" scope="page" />
		
		<c:forEach items="${resultMap.get('Searched')}" var="job" varStatus="i">
			<c:set var="company" value="${job.get('company')}"></c:set>
			<c:set var="title" value="${job.get('title')}"></c:set>
			<tr class="${i.index%2 == 0 ? 'even' : 'odd' }" onclick="getShortlistedCandidate('${company}', '${title}')" style="cursor: pointer;">
				<td>${company } </td>
				<td>${title}</td>
				<td>${job.get('datePosted') }</td>
				<td>${job.get('status') }</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>