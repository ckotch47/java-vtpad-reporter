package reporter;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// to save json from request  create test
class ResponseTestCreate {
    String duration;
    String create_at;
    String id;
    String name;
    String space_id;
    String update_at;
}

// to save json from request  create suite
class ResponseTestSuite {
    String id;
}

// класс для перехвата событий при выполнение теста
public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback, BeforeAllCallback {


    // объект для отображение результатов теста в консоли
    private final List<TestResultStatus> testResultsStatus = new ArrayList<>();

    // клиент http
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // вынести в настроки
    private final String spaceId = "2fa7d100-85e6-4e7f-a4a2-264c695f7b68";

    private final String testName = "ponimayu-ui test";

    private final String url = "http://195.2.79.224:5005";

    // вынести в настроки конец

    // объект для парса json
    private final Gson gson = new Gson();

    // переменная для хранения id
    private String suiteId = null;

    // хранение файла который содержит в себе id теста
    private final String pathTemp = "target/temp.txt";

    private String testId = null;
    // если тест упал
    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        System.out.print("Fail Test: " + context.getDisplayName() + '\n');
//        System.err.print(cause.getMessage() + '\n');
        try {
            this.CreateCase(context.getDisplayName(),"FAILED", cause.getMessage());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        testResultsStatus.add(TestResultStatus.FAILED);
    }

    // если тест отклонен
    @Override
    public void testAborted(ExtensionContext context, Throwable cause){
        System.out.print("Aborted Test: " + context.getDisplayName() + '\n');
        try {
            this.CreateCase(context.getDisplayName(),"UNKNOWN", cause.getMessage());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    // ессли тест выключен
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.print("Skipped Test: " + context.getDisplayName() + "||" + reason.orElse("No reason") + '\n');
        try {
            this.CreateCase(context.getDisplayName(),"SKIPPED", reason.orElse(null));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    // если тест прошел успешно
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.print("Success Test: " + context.getDisplayName() + '\n');
        try {
            this.CreateCase(context.getDisplayName(),"PASSED", null);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    // после всех тестов класса выводим в консоль summary
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(summary);
    }

    // перед запуском тестов в классе
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        // создаем файл если его нет
        if (!this.CheckFile(this.pathTemp)){
            this.CreateFile(this.pathTemp);
        }
        this.testId = this.GetFromFile(this.pathTemp);


        // делаем запрос на создания суита
        ResponseTestSuite responseTestSuite = this.CreateSuite(extensionContext.getDisplayName());
        this.suiteId = responseTestSuite.id;

        System.out.print("\n");
        System.out.print("Suite: " + extensionContext.getDisplayName() + '\n');
        System.out.print("\n");
    }

    private Boolean CheckFile(String path){
        File file = new File(path);
        return file.exists();
    }

    // создаем файл и записываем в него id теста
    private File CreateFile(String path) throws IOException, InterruptedException {
        ResponseTestCreate responseTestCreate = this.CreateTest(String.format("{\"name\": \"%s\"}", this.testName));
        File file = new File(path);
        try(FileWriter temp = new FileWriter(path, false)) {
            BufferedWriter writer = new BufferedWriter(temp);
            writer.write(responseTestCreate.id);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    // чтение id из файла
    private String GetFromFile(String path){
        int c;
        StringBuilder temp = new StringBuilder();
        try(FileReader fileReader = new FileReader(path)) {
            while((c=fileReader.read())!=-1){
                temp.append((char) c);
            }
            return temp.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }


    private HttpResponse<String> GetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) //"https://postman-echo.com/get"
                .timeout(Duration.ofSeconds(60))
                .GET()
                .build();

        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private ResponseTestCreate CreateTest(String jsonString) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/test/" + this.spaceId))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return this.gson.fromJson(response.body(), ResponseTestCreate.class);
    }

    private ResponseTestSuite CreateSuite(String name) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/suite/" + this.GetFromFile(this.pathTemp)))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"name\": \"%s\"}", name)))
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return this.gson.fromJson(response.body(), ResponseTestSuite.class);
    }

    private void CreateCase(String name, String status, String message) throws IOException, InterruptedException {
        String str = String.format("{\"name\": \"%s\", \"status\": \"%s\" ", name, status );
        if (message != null){
            message = message.replace('\n', ' ');
            message = message.replace('\"', '\'');
            message = message.replace('{', ' ');
            message = message.replace('}', ' ');
            str += String.format(",\"message\": \"%s\"", message);
        }
        str += "}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/cases/" + this.suiteId))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(str))
                .build();

        HttpResponse<String> temp = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.print(temp.body());
    }
}