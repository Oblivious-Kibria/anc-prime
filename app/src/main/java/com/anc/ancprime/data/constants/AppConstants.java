package com.anc.ancprime.data.constants;

public class AppConstants {


    // CUSTOMER ACTIVE STATUS;
    public static final String CUSTOMER_ACTIVE_STATUS_PENDING = "PENDING";
    public static final String CUSTOMER_ACTIVE_STATUS_ACTIVE = "ACTIVE";
    public static final String CUSTOMER_ACTIVE_STATUS_INACTIVE = "INACTIVE";




    // SEARCH CATEGORY;
    public static final String SEARCH_CATEGORY_TODAY = "Today";
    public static final String SEARCH_CATEGORY_THIS_WEEK = "This Week";
    public static final String SEARCH_CATEGORY_THIS_MONTH = "This Month";




    // ESP ACTIVE STATUS;
    public static final String ESP_ACTIVE_STATUS_PENDING = "PENDING";
    public static final String ESP_ACTIVE_STATUS_ACTIVE = "ACTIVE";
    public static final String ESP_ACTIVE_STATUS_INACTIVE = "INACTIVE";



    // ESP VERIFICATION STATUS;
    public static final String ESP_VERIFICATION_STATUS_VERIFIED_BY_ASM = "VERIFIED_BY_ASM";
    public static final String ESP_VERIFICATION_STATUS_VERIFIED_BY_RSM = "VERIFIED_BY_RSM";
    public static final String ESP_VERIFICATION_STATUS_VERIFIED_BY_NSM = "VERIFIED_BY_NSM";




    //Work-manager api repeating interval;
    public static final int WORK_MANAGER_REPEATING_INTERVAL_MINUTES = 15;
    public static final String WORKER_TAG = "ORDER_SUBMISSION_WORKER";




    // ORDER PROCESSING STATUS;
    public static final String ORDER_STATUS_INITIATED = "INITIATED";
    public static final String ORDER_STATUS_ACCEPTED_BY_DC = "ACCEPTED_BY_DC";
    public static final String ORDER_STATUS_READY_TO_SHIP = "READY_TO_SHIP";
    public static final String ORDER_STATUS_ON_THE_WAY = "ON_THE_WAY";
    public static final String ORDER_STATUS_DELIVERED = "DELIVERED";
    public static final String ORDER_STATUS_CANCELED = "CANCELED";




    // ORDER PROCESSING STATUS FOR DISPLAYING;
    public static final String ORDER_STATUS_PRIME_INITIATED = "Initiated";
    public static final String ORDER_STATUS_PRIME_ACCEPTED_BY_DC = "Accepted";
    public static final String ORDER_STATUS_PRIME_READY_TO_SHIP = "Ready";
    public static final String ORDER_STATUS_PRIME_ON_THE_WAY = "On the way";
    public static final String ORDER_STATUS_PRIME_DELIVERED = "Delivered";
    public static final String ORDER_STATUS_PRIME_CANCELED = "Canceled";
    public static final String ORDER_STATUS_PRIME_NOT_FOUND = "Not found";




    // ORDER APPROVAL STATUS;
    public static final String ORDER_APPROVAL_STATUS_PENDING = "APPROVAL_PENDING";
    public static final String ORDER_APPROVAL_STATUS_APPROVED_BY_ASM = "APPROVED_BY_ASM";
    public static final String ORDER_APPROVAL_STATUS_APPROVED_BY_RSH = "APPROVED_BY_RSH";
    public static final String ORDER_APPROVAL_STATUS_APPROVED_BY_NSM = "APPROVED_BY_NSM";
    public static final String ORDER_APPROVAL_STATUS_APPROVED_BY_ED = "APPROVED_BY_ED";
    public static final String ORDER_APPROVAL_STATUS_APPROVED_BY_HO = "APPROVED_BY_HO";




    // PAYMENT METHODS;
    public static final String PAYMENT_METHOD_CASH = "CASH";
    public static final String PAYMENT_METHOD_CHEQUE = "CHEQUE";
    public static final String PAYMENT_METHOD_BKASH = "BKASH";
    public static final String PAYMENT_METHOD_CARD = "CARD";
    public static final String PAYMENT_METHOD_BANK_TRANSFER = "BANK TRANSFER";




    // ORDER RETURN TYPES FOR DISPLAYING;
    public static final String RETURN_TYPE_FULL_RETURN = "Full Return";
    public static final String RETURN_TYPE_PARTIAL_RETURN = "Partial Return";



    // ORDER RETURN TYPES;
    public static final String RETURN_TYPE_PRIME_FULL_RETURN = "FULL";
    public static final String RETURN_TYPE_PRIME_PARTIAL_RETURN = "PARTIAL";




    // PUSH NOTIFICATION KEYS;
    public static final String PUSH_DETAILS_KEY = "PARTIAL";
    public static final String PUSH_TITLE_KEY = "PARTIAL";





    public static final String ST_TYPE_ESP = "ESP";




    public static final String INTENT_EXTRA_LOCATION_COORDINATES = "location_coordinates";
    public static final String INTENT_EXTRA_CUSTOMER_SEARCH_RESULT = "customer_search_result";
    public static final String INTENT_EXTRA_FROM_INVOICE_LIST_ACTIVITY = "InvoiceListActivity";




    public static final String USER_TYPE_ESP = "ESP";

    public static final String CUSTOMER_BALANCE_STATUS_DUE = "DUE";
    public static final String CUSTOMER_BALANCE_STATUS_ADVANCE = "ADVANCE";




    public static final int DISCOUNT_ENABLE = 1;
    public static final int DISCOUNT_DISABLE = 0;



    public static final int DEFAULT_CUSTOMER_ID = 0;
    public static final int DEFAULT_ESP_ID = 2;
    public static final String DEFAULT_EXPECTED_DATE = "2020-01-16";
    public static final int DEFAULT_ESP_DISCOUNT = 0;




    public static final int INTENT_LOCATION_REQUEST_CODE = 110;
    public static final int INTENT_CUSTOMER_SEARCH_REQUEST_CODE = 120;


}
