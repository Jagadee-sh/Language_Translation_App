public interface TranslationApi {
    @POST("translate")
    Call<TranslationResponse> translate(@Body TranslationRequest request);
}
