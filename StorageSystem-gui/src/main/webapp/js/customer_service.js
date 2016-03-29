'use strict';
 
App.factory('CustomerService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllCustomers: function() {
                    return $http.get('http://localhost:8080/StorageSystem-gui/api/customer/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching customers');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createCustomer: function(customer){
                    return $http.post('http://localhost:8080/StorageSystem-gui/api/customer/new', customer)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating customer');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateUser: function(user, id){
                    return $http.put('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id, user)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deleteCustomer: function(id){
                    return $http.get('http://localhost:8080/StorageSystem-gui/api/customer/'+id+'/delete')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting customer');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);