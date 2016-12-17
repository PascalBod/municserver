# municserver

This repository contains a server that can be used as a target for Mobile Device's CloudConnect webhook, for a [Munic device](https://www.munic.io/).

It shows how to decode JSON documents sent by CloudConnect platform.

Additionally, it provides information about how to run the server  as a _Docker_ container.

This server is not a production grade one. It aims at demonstrating how to decoded received JSON documents, not at providing a reliable, scalable source code able to handle tens of thousands of vehicles.

## Samples of decoded data

### Presence data

```
===== presence data
  Receipt time:  2016-12-16T17:30:20Z
  Id:            921092005562417562
  Connection id: 921092005558223260
  Asset:         356156060353505
  Time:          2016-12-16T17:30:47Z
  Type:          connect
  Reason:        idle_out
```

### Message data

```
===== message data
  Receipt time:  2016-12-16T17:31:03Z
  Id:            921092191365890458
  Asset:         356156060353505
  Parent id:     921086940399272346
  Connection id: 921092005558223260
  Type:          message
  Channel:       com.mdi.applications.obdstacks
  Sender:        356156060353505
  Recipient:     @@server@@
  Payload:       7B22726573756C7473223A5B7B22726573756C744C697374223A5B7B2264617461223A5B32332C3136305D2C22636F6D6D616E644E616D65223A224F42445F30313043227D5D2C22726573756C74223A22313531322E30222C22616374696F6E4C6973744E616D65223A2267657452504D227D5D7D - {"results":[{"resultList":[{"data":[23,160],"commandName":"OBD_010C"}],"result":"1512.0","actionListName":"getRPM"}]}
  Recorded at:   2016-12-16T17:31:31Z
  Received at:   2016-12-16T17:31:31Z
```

### Track data

```
===== track data
  Receipt time:  2016-12-16T17:43:40Z
  Id:            921095360317751706
  Asset:         356156060353505
  Recorded at:          2016-12-16T17:41:48.000Z
  Received at:          2016-12-16T17:44:07Z
  Index:                120
  Location (lat / lon): 43.60397 / 7.0014
  Fields:
    GPS_SPEED: 000098D5 - ....
    GPS_DIR: 00007B15 - ..{.
    MDI_RPM_MAX: 00000A68 - ...h
    MDI_RPM_MIN: 00000935 - ...5
    MDI_RPM_AVERAGE: 000009F5 - ....
    MDI_RPM_OVER: 01 - .
    MDI_MAX_RPM_IN_LAST_OVER_RPM: 00000000 - ....
    MDI_OBD_RPM: 00000EAF - ....

===== track data
  Receipt time:  2016-12-16T17:49:42Z
  Id:            921096878777434522
  Asset:         356156060353505
  Recorded at:          2016-12-16T17:49:30.000Z
  Received at:          2016-12-16T17:50:09Z
  Index:                190
  Location (lat / lon): 43.6375 / 6.9298
  Fields:
    BEHAVE_ACC_X_END: 000000B2 - ....
    BEHAVE_ACC_Z_END: FFFFFBE1 - ....
    BEHAVE_GPS_SPEED_BEGIN: 00003C38 - ..<8
    BEHAVE_GPS_SPEED_PEAK: 000046C1 - ..F.
    BEHAVE_GPS_SPEED_END: 00004ADE - ..J.
    BEHAVE_GPS_HEADING_BEGIN: 00038284 - ....
    BEHAVE_GPS_HEADING_PEAK: 00037744 - ..wD
    BEHAVE_GPS_HEADING_END: 0003818A - ....
    BEHAVE_ACC_X_BEGIN: 000000B5 - ....
    BEHAVE_ACC_X_PEAK: 000000D1 - ....
    BEHAVE_ACC_Y_BEGIN: 00000007 - ....
    BEHAVE_ACC_Y_PEAK: 00000000 - ....
    BEHAVE_ACC_Y_END: 00000010 - ....
    BEHAVE_ACC_Z_BEGIN: FFFFFBE7 - ....
    BEHAVE_ACC_Z_PEAK: FFFFFBEE - ....
    BEHAVE_ELAPSED: 00000898 - ....
    BEHAVE_UNIQUE_ID: 000000BF - ....
```

## Development environment

### Server side

* [_Java JDK 1.8.0_](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [_Eclipse Neon.1a - 4.6.1_](http://www.eclipse.org/downloads/)

The [JSON processing API jar and JSON processing RI jars](https://jsonp.java.net/download.html) must be added to the project, as external jars.

### Munic side

* _Munic.Box.V4.2G.SpecialOfferFF_
* _MunicOS - Box 2 v4.1_

## More information

Check the [wiki](https://github.com/PascalBod/municserver/wiki).