'use strict';
 
App.controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {
          var self = this;
          self.customer={id:null,name:'',phone:'',email:'',address:''};
          self.customers=[];
               
          self.fetchAllCustomers = function(){
              CustomerService.fetchAllCustomers()
                  .then(
                               function(d) {
                                    self.customers = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Currencies');
                                }
                       );
          };
            
          self.createCustomer = function(customer){
              CustomerService.createCustomer(customer)
                      .then(
                      self.fetchAllCustomers, 
                              function(errResponse){
                                   console.error('Error while creating Customer.');
                              } 
                  );
          };
 
         self.updateUser = function(user, id){
              UserService.updateUser(user, id)
                      .then(
                              self.fetchAllUsers, 
                              function(errResponse){
                                   console.error('Error while updating User.');
                              } 
                  );
          };
 
         self.deleteCustomer = function(id){
              CustomerService.deleteCustomer(id)
                      .then(
                              self.fetchAllCustomers, 
                              function(errResponse){
                                   console.error('Error while deleting Customer.');
                              } 
                  );
          };
 
          self.fetchAllCustomers();
 
          self.submit = function() {
              if(self.customer.id===null){
                  console.log('Saving New Customer', self.customer);    
                  self.createCustomer(self.customer);
              }else{
                  self.updateCustomer(self.customer, self.customer.id);
                  console.log('Customer updated with id ', self.customer.id);
              }
              self.reset();
          };
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.customers.length; i++){
                  if(self.customers[i].id === id) {
                     self.customer = angular.copy(self.customer[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.customer.id === id) {//clean form if the user to be deleted is shown there.
                 self.reset();
              }
              self.deleteCustomer(id);
          };
 
           
          self.reset = function(){
              self.customer={id:null,name:'',phone:'',email:'',address:''};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);