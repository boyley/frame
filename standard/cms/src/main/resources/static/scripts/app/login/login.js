'use strict';

angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'access',
                url: '/',
                data: {
                    roles: []
                },
                views: {
                    '': {
                        templateUrl: 'tpl/login/login.html'
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
