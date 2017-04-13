<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>

  <h3>InterViwes</h3>
  <br/><br/>
	<table style="width: auto;">
		<thead>
		<tr>
			<th>Interview</th>
			<th>Interviewer</th>
			<th>Date</th>
			<th>Result</th>
		</tr>
		</thead>
		<c:forEach items="${resultMap.get('InterViews')}" var="interview" varStatus="i">
			<c:set var="name" value="${interview.get('interview')}"></c:set>
			<c:set var="worksAt" value="${interview.get('interviewer')}"></c:set>
			<tr class="${i.index%2 == 0 ? 'even' : 'odd'}" onclick="getInterviewResults('${name}', '${worksAt}')" style="cursor: pointer;">
				<td>${name }</td>
				<td>${worksAt}</td>
				<td>${interview.get('date') }</td>
				<td>${interview.get('result') }</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>