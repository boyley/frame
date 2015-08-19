'use strict';

angular.module('app')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


