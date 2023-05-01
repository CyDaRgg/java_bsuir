package services;

import org.springframework.context.annotation.Scope;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryCaсhe<K,V>
{
 private Map<String, Response> cache= new HashMap<>();

 public String IsContains(String first, String second, String third, String fourth )
 {
  Set<String> temp = cache.keySet();

  for (String i : temp) {
   if (i.contains(first))
    if (i.contains(second))
     if (i.contains(third))
      if (i.contains(fourth)) {
       return i;
      }
  }
  return null;
 }
 public Response Get (String key)
 {
  return cache.get(key);
 }
 public void Put (String key, Response value)
 {
     cache.put(key, value);
 }

}
