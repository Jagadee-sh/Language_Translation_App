String[] languages = {"en", "es", "fr", "de", "it", "pt", "ru", "zh"};

ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinnerInputLanguage.setAdapter(adapter);
spinnerOutputLanguage.setAdapter(adapter);
