/**
 * Created by vsrinivasan on 5/8/2014.
 */

'use strict';

angular.module('csApp')
	.controller('CsOrderCtrl', function ($scope, $http) {
		console.log('enter CsOrderCtrl');
		var starListUrl = '/starcatalog/list';
		$scope.getStarList = function () {
			$http.get(starListUrl).
				success(function (data) {
					$scope.stars = data;
				}).error(function (data) {
					$scope.errors = data;
				})
		};

		$scope.getStarList();

		$scope.star = {};
		var addStarUrl = '/starcatalog/create';
		$scope.addStar = function() {
			console.log($scope.star);
			$http({
				url: addStarUrl,
				method: "POST",
				data: $scope.star
			}).then(function(response) {
				$scope.lastMessage = response;
				$scope.getStarList();
			}, function(response) {
				$scope.errors = response;
			});
		};

	})
;
