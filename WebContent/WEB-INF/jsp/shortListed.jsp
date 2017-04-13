<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>

  <h3>Short-Listed Candidates</h3>
  <br/><br/>
	<table style="width: auto;">
		<thead>
		<tr>
			<th>Name</th>
			<th>Works At</th>
			<th>Exp</th>
			<th>CTC</th>
		</tr>
		</thead>
		<c:forEach items="${resultMap.get('ShortListed')}" var="candidate" varStatus="i">
			<c:set var="name" value="${candidate.get('name')}"></c:set>
			<c:set var="worksAt" value="${candidate.get('worksAt')}"></c:set>
			<tr class="${i.index%2 == 0 ? 'even' : 'odd'}" onclick="getInterviewResults('${name}', '${worksAt}')" style="cursor: pointer;">
				<td>${name }</td>
				<td>${worksAt}</td>
				<td>${candidate.get('exp') }</td>
				<td>${candidate.get('ctc') }</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>