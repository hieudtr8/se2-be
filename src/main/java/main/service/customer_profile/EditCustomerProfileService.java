package main.service.customer_profile;

import main.model.CustomerProfile;
import main.repository.customer_profile.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditCustomerProfileService {
    @Autowired
    private CustomerProfileRepository customerProfileRepository;
    public static class CustomerProfileData {
        String name;
        Integer age;
        String phone;
        String address;
        String avatar;

        public CustomerProfileData(String name, Integer age, String phone, String address, String avatar) {
            this.name = name;
            this.age = age;
            this.phone = phone;
            this.address = address;
            this.avatar = avatar;
        }
    }
    public CustomerProfile editCustomerProfile(UUID id, CustomerProfileData customerProfileData) throws  Exception {
        CustomerProfile customerProfile = customerProfileRepository.getCustomerProfileById(id);
        if (customerProfile == null) {
            throw new Exception("Customer Profile not found!");
        }
        if(customerProfileData.name != null ){
            customerProfile.setName(customerProfileData.name);
        }
        if(customerProfileData.age != null ){
            customerProfile.setAge(customerProfileData.age);
        }
        if(customerProfileData.phone != null ){
            customerProfile.setPhone(customerProfileData.phone);
        }
        if(customerProfileData.address != null ){
            customerProfile.setAddress(customerProfileData.address);
        }
        if(customerProfileData.avatar != null ){
            customerProfile.setAvatar(customerProfileData.avatar);
        }
        customerProfileRepository.saveCustomerProfile(customerProfile);
        return customerProfile;
    }
}
