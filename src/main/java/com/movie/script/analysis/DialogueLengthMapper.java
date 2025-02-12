package com.movie.script.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DialogueLengthMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text character = new Text();
    private IntWritable wordCount = new IntWritable();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();
        if (line.isEmpty() || !line.contains(":")) return;

        String[] parts = line.split(":", 2);
        if (parts.length < 2) return;

        character.set(parts[0].trim());
        int dialogueLength = parts[1].trim().length();

        wordCount.set(dialogueLength);
        context.write(character, wordCount);
    }
}
