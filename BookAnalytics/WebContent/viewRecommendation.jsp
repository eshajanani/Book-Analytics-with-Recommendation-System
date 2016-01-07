
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
		<h2>Top 5 recommended books for the user : 44</h2>
			
			  <tr ng-repeat="a in recommend |filter:year " >
			    <td class="side">{{a.year}}</td>
			    
			  </tr>
			</table>
			</form>
</div>
