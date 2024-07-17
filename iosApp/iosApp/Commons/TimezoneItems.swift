//
//  TimezoneItems.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

class TimezoneItems: ObservableObject {
  @Published var timezones: [String] = []
  @Published var selectedTimezones = Set<String>()

    init(timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()) {
      self.timezones = timezoneHelper.getTimeZoneStrings()
  }
}

