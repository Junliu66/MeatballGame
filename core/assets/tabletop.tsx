<?xml version="1.0" encoding="UTF-8"?>
<tileset name="tabletop" tilewidth="32" tileheight="32" tilecount="36" columns="6">
 <image source="tabletop.png" width="192" height="192"/>
 <terraintypes>
  <terrain name="table" tile="0">
   <properties>
    <property name="solid" type="bool" value="false"/>
   </properties>
  </terrain>
  <terrain name="floor" tile="0">
   <properties>
    <property name="floor" type="bool" value="true"/>
   </properties>
  </terrain>
  <terrain name="checkerboard" tile="0">
   <properties>
    <property name="finishline" type="bool" value="true"/>
   </properties>
  </terrain>
 </terraintypes>
 <tile id="0" terrain="2,2,2,2"/>
 <tile id="1" terrain="2,2,2,2"/>
 <tile id="2" terrain="2,2,2,2"/>
 <tile id="3" terrain="2,2,2,0"/>
 <tile id="4" terrain="2,2,0,0"/>
 <tile id="5" terrain="2,2,0,2"/>
 <tile id="6" terrain="0,0,0,2"/>
 <tile id="7" terrain="0,0,2,0"/>
 <tile id="8" terrain="2,2,2,2"/>
 <tile id="9" terrain="2,0,2,0"/>
 <tile id="10" terrain="0,0,0,0"/>
 <tile id="11" terrain="0,2,0,2"/>
 <tile id="12" terrain="0,2,0,0"/>
 <tile id="13" terrain="2,0,0,0"/>
 <tile id="14" terrain="2,2,2,2"/>
 <tile id="15" terrain="2,0,2,2"/>
 <tile id="16" terrain="0,0,2,2"/>
 <tile id="17" terrain="0,2,2,2"/>
 <tile id="18" terrain="1,1,1,1"/>
 <tile id="19" terrain="1,1,1,1"/>
 <tile id="20" terrain="1,1,1,1"/>
 <tile id="21" terrain="1,1,1,0">
  <objectgroup draworder="index"/>
 </tile>
 <tile id="22" terrain="1,1,0,0">
  <objectgroup draworder="index"/>
 </tile>
 <tile id="23" terrain="1,1,0,1"/>
 <tile id="24" terrain="0,0,0,1"/>
 <tile id="25" terrain="0,0,1,0"/>
 <tile id="26" terrain="1,1,1,1"/>
 <tile id="27" terrain="1,0,1,0">
  <objectgroup draworder="index"/>
 </tile>
 <tile id="28" terrain="0,0,0,0">
  <objectgroup draworder="index">
   <object id="1" x="-0.217391" y="-0.130435" width="32.1304" height="32.1739"/>
  </objectgroup>
 </tile>
 <tile id="29" terrain="0,1,0,1">
  <objectgroup draworder="index"/>
 </tile>
 <tile id="30" terrain="0,1,0,0"/>
 <tile id="31" terrain="1,0,0,0"/>
 <tile id="32" terrain="1,1,1,1"/>
 <tile id="33" terrain="1,0,1,1"/>
 <tile id="34" terrain="0,0,1,1">
  <objectgroup draworder="index"/>
 </tile>
 <tile id="35" terrain="0,1,1,1"/>
 <wangsets>
  <wangset name="New Wang Set" tile="-1"/>
 </wangsets>
</tileset>
