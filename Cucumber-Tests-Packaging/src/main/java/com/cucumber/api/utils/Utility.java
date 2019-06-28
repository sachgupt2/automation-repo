package com.cucumber.api.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.stream.Collectors;

public class Utility
{

   public static String generateStringFromResource( String absolutePath ) throws Exception
   {
      String path = ClassLoader.getSystemClassLoader().getResource( "." ).getPath() + absolutePath;
      FileInputStream in = new FileInputStream( path );

      String value = new BufferedReader( new InputStreamReader( in ) ).lines().collect( Collectors.joining( "\n" ) );
      in.close();
      return value;
   }

   public static String getPropertyValue( String propertyName ) throws IOException
   {
      String path = ClassLoader.getSystemClassLoader().getResource( "." ).getPath() + "/properties/api.properties";
      Properties prop = new Properties();
      prop.load( new FileInputStream( path ) );
      return prop.getProperty( propertyName );
   }

}
