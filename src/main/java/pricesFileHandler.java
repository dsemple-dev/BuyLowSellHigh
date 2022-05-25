/*
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class pricesFileHandler {
	
	public pricesFileHandler()
	{
		//empty constructor
	}
	
	public Float[] getPricesFromFile(String fName)
	{
		String[] stringArray = null;
		try(Stream<String> lines = 
					Files.lines(Path.of(fName), Charset.defaultCharset())){
			stringArray = lines.flatMap(line -> Arrays.stream(line.split(",")))
					.toArray(size -> new String[size]);
				}
		catch (Exception ex) {
			System.out.println(ex.toString());
		}

		Float[] floats = Arrays.stream(stringArray).map(Float::valueOf).toArray(Float[]::new);
		return floats;
	}
}*/
