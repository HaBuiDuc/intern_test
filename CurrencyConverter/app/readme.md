# Explanation of the app's structure

This project use MVVM architecture

# Steps to build and run the app

Install Android Studio
Install some required SDK such as Android SDK or JDK
Open the app in Android Studio and run with an emulator or physical device

# Additional notes or challenges encountered during development

## Notes

## Challenges

I have some difficulties with an API to get currency rates.
First, I tried to use ExchangeRatesAPI and Open Exchange Rates APIs as you recommended, but I
encountered some problems. Therefore, I switched to CurrencyAPI, but the free plan does not support
currency conversion, so I have to multiply values to convert them.

Second, CurrencyAPI may not return the latest values since the data returned from the API does not
match the values I find on Google. However, I have checked and confirmed that the converted values
from the API and the values shown in the UI are exactly correct.

# Demo video

[Currency Converter App Demo Video](https://www.veed.io/view/adde32fa-b6fd-41d4-bdea-ad9015101543?panel=share)
