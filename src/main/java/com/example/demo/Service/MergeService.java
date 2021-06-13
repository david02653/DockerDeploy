package com.example.demo.Service;

import com.example.demo.entity.Rasa.legacy.AllConfig;
import com.example.demo.entity.Rasa.v2.action.ActionFile;
import com.example.demo.entity.Rasa.v2.domain.Setting;
import com.example.demo.entity.Rasa.v2.nlu.NluFile;
import com.example.demo.entity.Rasa.v2.story.StoryFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class MergeService {

    // read application.properties data
    private final Environment env;

    private final MergeSetting generator = new MergeSetting();
    private final YmlReader reader = new YmlReader();
    private final YmlWriter writer = new YmlWriter();
    private final ConfigureService configureService = new ConfigureService();
    // current settings
    private NluFile nluFile;
    private StoryFile storyFile;
    private ActionFile actionFile;
    private HashMap<String, HashMap<String, Setting>> domainFile;

    public MergeService(Environment env) {
        // load current setting files: nlu, story, action, domain
        // extract settings from current file
        if(configureService.runSpecShell("extractSetting.sh"))
            System.out.println("Setting extraction success.");
        else
            System.out.println("failed to extract current settings !");
        // load settings
        this.nluFile = reader.loadNlu(env.getProperty("rasa.setting.nlu"));
        this.storyFile = reader.loadStory(env.getProperty("rasa.setting.story"));
        this.actionFile = reader.loadAction(env.getProperty("rasa.setting.action"));
        HashMap<String, ArrayList<String>> rawDomainMap = reader.loadDomainAsString(env.getProperty("rasa.setting.domain"));
        this.domainFile = reader.parseDomainMap(rawDomainMap);
//        reloadCurrentSettingFile();
        this.env = env;
    }

    /**
     * load current setting file again
     */
    public void reloadCurrentSettingFile(){
        // load current setting files: nlu, story, action, domain
        this.nluFile = reader.loadNlu(env.getProperty("rasa.setting.nlu"));
        this.storyFile = reader.loadStory(env.getProperty("rasa.setting.story"));
        this.actionFile = reader.loadAction(env.getProperty("rasa.setting.action"));
        HashMap<String, ArrayList<String>> rawDomainMap = reader.loadDomainAsString(env.getProperty("rasa.setting.domain"));
        this.domainFile = reader.parseDomainMap(rawDomainMap);
    }

    /**
     * testing method
     * ignore this
     */
    public void loadCurrentNluFile(){
        nluFile = reader.loadNlu(env.getProperty("rasa.setting.nlu"));
    }

    /**
     * testing method
     * ignore this
     */
    public void testInitLoad(){
        System.out.println(nluFile);
    }

    /**
     * merge current settings with new settings
     * export to result file
     * @param input new settings
     * @throws IOException io error
     */
    public void mergeAll(String input) throws IOException {
        AllConfig config = new AllConfig();
        // split input to nlu, domain, stories and action
        String[] configToken = input.split("---\n");
        for(String token: configToken){
            if(token.split("\n")[0].startsWith("nlu")){
                // nlu file
                config.setNlu(new ArrayList<String>(Arrays.asList(token.split("\n"))));
            }else if(token.split("\n")[0].startsWith("stories")){
                // stories file
                config.setStories(new ArrayList<String>(Arrays.asList(token.split("\n"))));
            }else if(token.split("\n")[0].startsWith("version")){
                // domain file
                config.setDomain(new ArrayList<String>(Arrays.asList(token.split("\n"))));
            }else{
                // action file
                config.setAction(new ArrayList<String>(Arrays.asList(token.split("\n"))));
            }
        }
        // merge each file
        NluFile mergedNlu = generator.mergeNlu(nluFile, reader.loadNlu(config.getNlu()));
        StoryFile mergedStories = generator.mergeStory(storyFile, reader.loadStory(config.getStories()));
        ActionFile mergedAction = generator.mergeAction(actionFile, reader.loadAction(config.getAction()));
        HashMap<String, HashMap<String, Setting>> mergedDomain = generator.mergeDomain(domainFile, reader.loadDomain(config.getDomain()));
        // valid domain file
        mergedDomain = validDomain(mergedDomain);
        // export merged result file
        writer.writeNlu(env.getProperty("rasa.setting.result.nlu"), mergedNlu);
        writer.writeStory(env.getProperty("rasa.setting.result.story"), mergedStories);
        writer.writeAction(env.getProperty("rasa.setting.result.action"), mergedAction);
        writer.writeDomain(env.getProperty("rasa.setting.result.domain"), mergedDomain);
    }

    public void mergeNlu(String input) throws IOException{
        NluFile mergedNlu = generator.mergeNlu(nluFile, reader.loadNlu(new ArrayList<String>(Arrays.asList(input.split("\n")))));
        writer.writeNlu(env.getProperty("rasa.setting.result.nlu"), mergedNlu);
    }

    public void mergeStories(String input) throws IOException{
        StoryFile mergedStories = generator.mergeStory(storyFile, reader.loadStory(new ArrayList<String>(Arrays.asList(input.split("\n")))));
        writer.writeStory(env.getProperty("rasa.setting.result.story"), mergedStories);
    }

    public void mergeAction(String input) throws IOException{
        ActionFile mergedAction = generator.mergeAction(actionFile, reader.loadAction(new ArrayList<String>(Arrays.asList(input.split("\n")))));
        writer.writeAction(env.getProperty("rasa.setting.result.action"), mergedAction);
    }

    public void mergeDomain(String input) throws IOException{
        HashMap<String, HashMap<String, Setting>> mergedDomain = generator.mergeDomain(domainFile, reader.loadDomain(new ArrayList<String>(Arrays.asList(input.split("\n")))));
        // valid domain file
        mergedDomain = validDomain(mergedDomain);
        writer.writeDomain(env.getProperty("rasa.setting.result.domain"), mergedDomain);
    }

    /**
     * check if domain file have empty entities
     * put something in if empty
     * in this case, insert SpacyEntityExtractor label 'DATE'
     * note that this entity requires dimension definition in config.yml to work
     * @param file domain file
     */
    public HashMap<String, HashMap<String, Setting>> validDomain(HashMap<String, HashMap<String, Setting>> file){
        HashMap<String, Setting> entityList = file.get("entities");
        if(entityList.size() <= 0){
            // insert SpacyEntityExtractor label 'DATE'
            Setting date = new Setting("DATE");
            entityList.put("DATE", date);
            file.put("entities", entityList);
        }
        return file;
    }

}
