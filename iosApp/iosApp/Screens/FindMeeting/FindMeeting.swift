//
//  FindMeeting.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FindMeeting: View {

    @EnvironmentObject private var timezoneItems: TimezoneItems
    @State private var meetingHours = [Int]()
    @State private var showHoursDialog = false
    @State private var startDate = Calendar.current.date(bySettingHour: 8, minute: 0, second: 0, of: Date()) ?? Date()
    @State private var endDate = Calendar.current.date(bySettingHour: 17, minute: 0, second: 0, of: Date()) ?? Date()

    private var timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()

    var body: some View {
        NavigationView {
            VStack(spacing: 8) {
                Spacer()
                Form {
                    Section(header: Text("Time Range")) {
                        DatePicker("Start Time", selection: $startDate, displayedComponents: .hourAndMinute)
                        DatePicker("End Time", selection: $endDate, displayedComponents: .hourAndMinute)
                    }
                    Section(header: Text("Time Zones")) {
                        ForEach(Array(timezoneItems.selectedTimezones), id: \.self) { timezone in
                            HStack {
                                Text(timezone)
                                Spacer()
                            }
                        }
                    }
                }
                Spacer()
                Button(
                    action: {search()},
                    label: {
                        Text("Search").foregroundColor(Color.black)
                    })
                Spacer()
            }
            .navigationTitle("Find Meeting Time")
            .sheet(isPresented: $showHoursDialog) {
                HourSheet(hours: $meetingHours)
            }
        }
    }

    private func search() {
        let startHour = Calendar.current.component(.hour, from: startDate)
        let endHour = Calendar.current.component(.hour, from: endDate)

        let hours = timezoneHelper.search(
            startHour: Int32(startHour),
            endHour: Int32(endHour),
            timeZoneStrings: Array(timezoneItems.selectedTimezones)
        ).map { kotlinHour in
            Int(truncating: kotlinHour)
        }

        meetingHours.removeAll()
        meetingHours.append(contentsOf: hours)

        showHoursDialog = true
    }
}

struct FindMeeting_Previews: PreviewProvider {
    static var previews: some View {
        FindMeeting()
    }
}
