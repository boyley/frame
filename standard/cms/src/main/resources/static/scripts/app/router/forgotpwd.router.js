'use strict';

angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('forgotpwd', {
                parent: 'access',
                url: '/forgotpwd',
                data: {
                    roles: []
                },
                views: {
                    '': {
                        templateUrl: 'tpl/forgotpwd.html'
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
