'use strict';

angular.module('app').controller('AsideCtrl',['$translate', '$translatePartialLoader',function($translate,$translatePartialLoader) {
    $translatePartialLoader.addPart('aside');
    $translate.refresh();
}]);