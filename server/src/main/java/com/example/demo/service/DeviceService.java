package com.example.demo.service;

import com.example.demo.helper.DateDeserializer;
import com.example.demo.model.domains.Device;
import com.example.demo.model.dto.DeviceDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * This will consume the url endpoint to return us the list of devices currently available
     * this method could be turn to be more generic to serve multiple services but for this demo it will only
     * serve one purpose
     *
     * @return this will return a list of devices
     * @throws ValidationException this will throw a validation failed exception if the status isn't 200 or 201
     */
    private List<DeviceDto> getDevicesFromUrl() throws ValidationException {
       /*
          if we are going to fetch using this url then the following code would be written to consume
          the url endpoint resources, otherwise for now we will use a preset data as provided for the test
         */
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://api.cybergator.co.uk/testing/devices");
        /*
          initialize and create the headers if required
         */
        HttpEntity<Void> entity = new HttpEntity<>(new HttpHeaders());
        /*
          return the http response and check if the status code is 200 or 201 then return the devices
          otherwise throw the ValidationException
         */
        ResponseEntity<List<Map<String, DeviceDto>>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Map<String, DeviceDto>>>() {
        });
        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            /**
             * stream through the devices mapping(returning) only the values which returns collections
             * then flatmap the whole collection and return a device list as required
             */
            return response.getBody().stream().map(Map::values).flatMap(Collection::stream).collect(Collectors.toList());
        } else {
            throw new ValidationException("Error occurred while trying to get the resources");
        }
    }

    /**
     * these are devices that have been already been setup, this is for a demo only as suggested
     * to use a url you would have to call the method above to serve your purpose
     *
     * @return
     */
    private List<DeviceDto> getPresetDevices() {
        String data = "[{ \"2b727991-5ddb-4a42-82eb-13bbb2876a28\": { \"agentVersion\": \"0.1.0\", \"alertIds\": [\"0a45fa06-87ad-4ef7-ae30-f05d42beca22\", \"16597623-a5d1-4a21-8304-58c458aefd3b\"], \"architecture\": \"x64\", \"collector\": {\"tenantId\": \"496e3cfd-672c-47ae-9dfb-0d840b0aed80\", \"collectorId\": \"5316b276-18cc-4692-b89b-26ae22a0c4ef\", \"collectorName\": \"Test_Collector\"}, \"cpuModel\": \"Intel(R) Xeon(R) CPU E5-2673 v3 @ 2.40GHz\", \"cpuNumber\": 1, \"description\": \"Auriga Test Device\", \"deviceId\": \"2b727991-5ddb-4a42-82eb-13bbb2876a28\", \"discoveryDate\": \"2019-05-21T16:02:56.819\", \"externalIp\": \"144.0.1.163\", \"externalIpCordLat\": \"52.35\", \"externalIpCordLong\": \"4.9167\", \"externalIpDateUpdated\": \"2019-06-10T09:33:07.932\", \"ipAddresses\": [{\"ipAddress\": \"1.0.0.4\", \"ipFamily\": \"IPv4\", \"macAddress\": \"01:1d:3a:20:da:2d\"},{\"ipAddress\": \"10.0.0.1\", \"ipFamily\": \"IPv4\", \"macAddress\": \"02:1d:3a:20:da:2d\"}], \"isAgentConnected\": true, \"lastSeenDate\": \"2019-06-10T09:46:00.176Z\", \"name\": \"AurigaDC01\", \"osCode\": \"Windows_NT\", \"platform\": \"win32\", \"registeredDate\": \"2019-05-21T16:02:56.819\", \"release\": \"6.3.9600\", \"updatesScriptLastRun\": \"2019-06-10T09:24:10\" }}]";

        /*
         * deserialize date to give use the proper date without gson complaining due to timezone
         */
        Gson gson = new Gson().newBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();

        /*
         * we then map the json responce to match our data type
         */
        Type mapStringObjectType = new TypeToken<List<Map<String, DeviceDto>>>() {
        }.getType();
        List<Map<String, DeviceDto>> Devices = gson.fromJson(data, mapStringObjectType);

        /**
         * stream through the devices mapping(returning) only the values which returns collections
         * then flatmap the whole collection and return a device list as required
         */
        return Devices.stream().map(Map::values).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * this will return our domain object devices
     *
     * @return a list of devices
     */
    public List<Device> getDevices() {

        /**
         * if we need to consume the data from getDevicesFromUrl instead just
         * replace this.getPresetDevices() with this.getDevicesFromUrl() instead
         * or create another function to handle it by replicating the logic below and replacing
         * where the data is pulled from which is (getDevicesFromUrl)
         */
        return this.getPresetDevices().stream().map(device -> {
            //map the dto to the domain object
            Device d = new ModelMapper().map(device, Device.class);
            d.setHowManyAlerts(device.getAlertIds().size());
            d.setIpAddresses(device.getIpAddresses().stream().map(r -> r.get("ipAddress")).collect(Collectors.toList()));
            return d;
        }).collect(Collectors.toList());
    }

}
