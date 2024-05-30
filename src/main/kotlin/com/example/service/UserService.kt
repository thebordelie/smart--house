package com.example.service

import com.example.model.Device
import com.example.model.UserEmulator

class UserService {
    var users: HashMap<UserEmulator, ArrayList<Device>> = HashMap()

    var requestGenerator: Generator = RequestGenerator()



}