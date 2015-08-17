'use strict';

angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.menu', {
                url: '/menu',
                data: {
                    roles: []
                },
                views: {
                    'content': {
                        templateUrl: 'tpl/menu.html'
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
