//
//  TimezoneView.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TimezoneView: View {
    @EnvironmentObject private var timezoneItems: TimezoneItems
    @State private var currentDate = Date()
    @State private var showTimezoneDialog = false

    private var timezoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    private let timer = Timer.publish(
        every: 1000,
        on: .main,
        in: .common
    ).autoconnect()

    var body: some View {
        NavigationView {
            VStack(spacing: 4) {
                TimeCard(
                    timezone: timezoneHelper.currentTimeZone(),
                    time: DateFormatter.shortTime.string(from: currentDate),
                    date: DateFormatter.longDate.string(from: currentDate)
                )
                Spacer()
                List {
                    ForEach(Array(timezoneItems.selectedTimezones), id: \.self) { timezone in
                        NumberTimeCard(
                            timezone: timezone,
                            time: timezoneHelper.getTime(zoneId: timezone),
                            hours: "\(timezoneHelper.hoursFromTimeZone(otherZoneId: timezone)) hours from local",
                            date: timezoneHelper.getDate(zoneId: timezone)
                        )
                        .withListModifier()
                    }
                    .onDelete(perform: deleteItems)
                }

                .listStyle(.plain)
            }
            .onReceive(timer) { currentDate = $0 }
            .navigationTitle("World Clocks")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        showTimezoneDialog = true
                    }) {
                        Image(systemName: "plus").frame(alignment: .trailing).foregroundColor(.black)
                    }
                }
            }
        }
        .fullScreenCover(isPresented: $showTimezoneDialog) {
            TimezoneDialog().environmentObject(timezoneItems)
        }
    }

    func deleteItems(at offsets: IndexSet) {
        let timezoneArray = Array(timezoneItems.selectedTimezones)
        for index in offsets {
            let element = timezoneArray[index]
            timezoneItems.selectedTimezones.remove(element)
        }
    }
}

struct TimezoneView_Previews: PreviewProvider {
    static var timezoneItems: TimezoneItems {
        var timezoneItems = TimezoneItems()
        timezoneItems.timezones.append("Africa/Accra")
        timezoneItems.timezones.append("Africa/Algiers")
        timezoneItems.selectedTimezones.insert("Africa/Accra")
        timezoneItems.selectedTimezones.insert("Africa/Algiers")
        return timezoneItems
    }

    static var previews: some View {
        TimezoneView().environmentObject(timezoneItems)
    }
}
