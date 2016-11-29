package com.cn.about.javaObjToJson;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JavaObjToJson {
	 private static Gson gson=null;
	    static{
	        if(gson==null){
	            gson=new Gson();
	        }
	    }
	    private JavaObjToJson(){}
	    /**
	     * ������ת����json��ʽ
	     * @param ts
	     * @return
	     */
	    public static String objectToJson(Object ts){
	        String jsonStr=null;
	        if(gson!=null){
	            jsonStr=gson.toJson(ts);
	        }
	        return jsonStr;
	    }
	    /**
	     * ������ת����json��ʽ(���Զ������ڸ�ʽ)
	     * @param ts
	     * @return
	     */
	    public static String objectToJsonDateSerializer(Object ts,final String dateformat){
	        String jsonStr=null;
	        gson=new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
	            public JsonElement serialize(Date src, Type typeOfSrc,
	                    JsonSerializationContext context) {
	                SimpleDateFormat format = new SimpleDateFormat(dateformat);
	                return new JsonPrimitive(format.format(src));
	            }
	        }).setDateFormat(dateformat).create();
	        if(gson!=null){
	            jsonStr=gson.toJson(ts);
	        }
	        return jsonStr;
	    }
	    /**
	     * ��json��ʽת����list����
	     * @param jsonStr
	     * @return
	     */
	    public static List<?> jsonToList(String jsonStr){
	        List<?> objList=null;
	        if(gson!=null){
	            java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<List<?>>(){}.getType();
	            objList=gson.fromJson(jsonStr, type);
	        }
	        return objList;
	    }
	    /**
	     * ��json��ʽת����map����
	     * @param jsonStr
	     * @return
	     */
	    public static Map<?,?> jsonToMap(String jsonStr){
	        Map<?,?> objMap=null;
	        if(gson!=null){
	            java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<Map<?,?>>(){}.getType();
	            objMap=gson.fromJson(jsonStr, type);
	        }
	        return objMap;
	    }
	    /**
	     * ��jsonת����bean����
	     * @param jsonStr
	     * @return
	     */
	    public static Object  jsonToBean(String jsonStr,Class<?> cl){
	        Object obj=null;
	        if(gson!=null){
	            obj=gson.fromJson(jsonStr, cl);
	        }
	        return obj;
	    }
	    /**
	     * ��jsonת����bean����
	     * @param jsonStr
	     * @param cl
	     * @return
	     */
	    @SuppressWarnings("unchecked")
	    public static <T> T  jsonToBeanDateSerializer(String jsonStr,Class<T> cl,final String pattern){
	        Object obj=null;
	        gson=new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	            public Date deserialize(JsonElement json, Type typeOfT,
	                    JsonDeserializationContext context)
	                    throws JsonParseException {
	                    SimpleDateFormat format=new SimpleDateFormat(pattern);
	                    String dateStr=json.getAsString();
	                try {
	                    return format.parse(dateStr);
	                } catch (ParseException e) {
	                    e.printStackTrace();
	                }
	                return null;
	            }
	        }).setDateFormat(pattern).create();
	        if(gson!=null){
	            obj=gson.fromJson(jsonStr, cl);
	        }
	        return (T)obj;
	    }
	    /**
	     * ����
	     * @param jsonStr
	     * @param key
	     * @return
	     */
	    public static Object  getJsonValue(String jsonStr,String key){
	        Object rulsObj=null;
	        Map<?,?> rulsMap=jsonToMap(jsonStr);
	        if(rulsMap!=null&&rulsMap.size()>0){
	            rulsObj=rulsMap.get(key);
	        }
	        return rulsObj;
	    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		//1.����ʵ��תΪjsonString
//		People people=new People();
//		people.setId("1");
//		people.setName("����");
//		people.setAge(26);
//		people.setPhonenum("13212345678");
//		people.setAddress("�V����Ӆ^");
//		Student stu=new Student();
//		stu.setStuId("stuID1");
//		stu.setStuName("����ѧ��");
//		people.setStudent(stu);
//		
//		System.out.println(objectToJson(people));
		
		//2.����jsong��ʽ�ַ���ת��bean
//		String jsonStr="{\"id\":\"1\",\"name\":\"����\",\"age\":26,\"phonenum\":\"13212345678\",\"address\":\"�V����Ӆ^\",\"student\":{\"stuId\":\"stuID1\",\"stuName\":\"����ѧ��\"}}";
//		People test=(People)jsonToBean(jsonStr,People.class);
//		System.out.println(test.getName());
//		System.out.println(test.getStudent().getStuName());
	}

}
