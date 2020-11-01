package com.qa.java;

public class invalidZero {
		public static void main (String[] args) {
	    int arr[] = {2,4,5,0,0,5,5,4,8,6,0,6,8,8};
	    for(int ele=0;ele<arr.length-1;ele++){
	      for(int j=ele+1; j<arr.length; j++){
	        if(arr[j]==0){
	          continue;
	        }
	        if(arr[ele] == arr[j]){
	          arr[ele] = 0;
	          arr[j] = 2*arr[j];
	        }
	        break; 
	      }
	    }
	    int temp;
	    for(int ele=0;ele<arr.length-1;ele++){
	      for(int j=ele+1; j<arr.length; j++){
	        if(arr[ele]==0){
	          temp = arr[ele];
	          arr[ele] = arr[j];
	          arr[j] = temp;
	        }}
	        
	    }
	    for(int s_ele:arr){
	      System.out.print(s_ele);
	    }
		
		}
	}
