package com.katalon.plugin.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.katalon.platform.api.console.PluginConsoleOption;
import com.katalon.platform.api.extension.LauncherOptionParserDescription;
import com.katalon.platform.api.model.TestCaseEntity;
import com.katalon.plugin.tags.constants.TagsConstants;
import com.katalon.plugin.tags.entity.StringConsoleOption;

public class TagsLauncherOptionParserDescription implements LauncherOptionParserDescription {

	private PluginConsoleOption<?> tagConsoleOption = new StringConsoleOption() {
		@Override
		public String getOption() {
			return TagsConstants.TAGS_CONSOLE_OPTION;
		}
	};

	@Override
	public List<PluginConsoleOption<?>> getConsoleOptionList() {
		return Arrays.asList(tagConsoleOption);
	}

	@Override
	public void onConsoleOptionDetected(com.katalon.platform.api.console.PluginConsoleOption<?> arg0) {
		if (arg0.getOption().equals(TagsConstants.TAGS_CONSOLE_OPTION)) {
			System.out.println(TagsConstants.TAGS_CONSOLE_OPTION + " is recognized with value " + arg0.getValue());
		}
	}

	@Override
	public List<TestCaseEntity> onPreExecution(List<TestCaseEntity> arg0) {
		List<TestCaseEntity> filteredTestCases = new ArrayList<>();
		arg0.stream().forEach(a -> {
			if(Arrays.asList(a.getTags().split(",")).contains(tagConsoleOption.getOption())){
				filteredTestCases.add(a);
			}
		});
		return filteredTestCases;
	}

}
