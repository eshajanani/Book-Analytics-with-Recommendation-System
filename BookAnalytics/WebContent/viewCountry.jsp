
<div class="container">
    <form>
   				<table>
					<tr>
						<td><input type="text" ng-model="year" size="30" placeholder=" Search for author or year"></td>
						
						
					</tr>
				</table>
		    </form>
		    
		<form id="main">
		<h2> Top 5 popular books in USA for the year of 2003</h2>
		<table >
			
			  <tr ng-repeat="a in country |filter:year |filter:author" >
			    <td class="side">{{a.year}}</td>
			    
			  </tr>
			</table>
			</form>
</div>
