
<div class="container">
    <form>
   				<table>
					<tr>
						<td><input type="text" ng-model="year" size="30" placeholder=" Search for state or year"></td>
						
						
					</tr>
				</table>
		    </form>
		    
		<form id="main">
		<table >
		<h2> Top 5 books for the year 2003 at New Jersey</h2>
			
			  <tr ng-repeat="s in state |filter:year |filter:author" >
			    <td class="side">{{s.year}}</td>
			    
			  </tr>
			</table>
			</form>
</div>
