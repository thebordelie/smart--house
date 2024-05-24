package com.example.smarthouse.config

enum class DeviceType {
    AIR_CONDITIONER {
        override fun deviceName(): String = "Conditioner"
    },

    COFFEE_MACHINE {
        override fun deviceName(): String = "Coffee machine"
    },

    HUMIDIFIER {
        override fun deviceName(): String = "Humidifier"
    },

    LIGHT {
        override fun deviceName(): String = "Light"
    },

    ROOM_HEATING {
        override fun deviceName(): String = "Room heating"
    },

    TEAPOT {
        override fun deviceName(): String = "Teapot"
    },

    VACUUM_CLEANER {
        override fun deviceName(): String = "Vacuum cleaner"
    };

    abstract fun deviceName(): String

}