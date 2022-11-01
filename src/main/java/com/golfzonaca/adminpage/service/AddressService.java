package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Address;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AddressService {

     Address save(Address address);

     Optional<Address> findByAddressId(Long id);
}
