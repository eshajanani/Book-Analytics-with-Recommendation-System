
<div class="container">
    <form>
   				<table>
					<tr>
						<td><input type="text" ng-model="year" size="30" placeholder=" Search for publisher or year"></td>
						
						
					</tr>
				</table>
		    </form>
		    <br>
		    
		    
		<form id="main">
		<table >
			<tr><th>YEAR </th><th>Publisher </th><th>RATINGS </th></tr>
			
			  <tr ng-repeat="p in publisher |filter:year " >
			    <td class="side">{{p.year}}</td>
			    <td class="side">{{p.author}}</td>
			    <td class="side">{{p.avgRate}}</td>
			  </tr>
			</table>
			</form>
</div>
