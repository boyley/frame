'use strict';

angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('register', {
                parent: 'access',
                url: '/register',
                data: {
                    roles: []
                },
                views: {
                    '': {
                        templateUrl: 'tpl/register/register.html'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            });
    });
