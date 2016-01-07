
<div class="container">
    <form>
   				<table>
					<tr>
						<td><input type="text" ng-model="year" size="30" placeholder=" Search for author or year"></td>
						
						
					</tr>
				</table>
		    </form>
		    
		<form id="main">
		<table >
			<tr><th>YEAR </th><th>AUTHOR </th><th>RATINGS </th></tr>
			  <tr ng-repeat="a in authors |filter:year |filter:author" >
			    <td class="side">{{a.year}}</td>
			    <td class="side">{{a.author}}</td>
			    <td class="side">{{a.avgRate}}</td>
			  </tr>
			</table>
			</form>
</div>
