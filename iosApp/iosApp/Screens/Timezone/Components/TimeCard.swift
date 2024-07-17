//
//  TimezoneCard.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TimeCard: View {
    var timezone: String
    var time: String
    var date: String

    var body: some View {
        HStack(alignment: .bottom) {
            cityColumn
            Spacer()
            dateTimeColumn
        }
        .padding(EdgeInsets(top: 0, leading: 8, bottom: 16, trailing: 8))
        .frame(
            maxWidth: .infinity,
            minHeight: 120,
            alignment: Alignment(horizontal: .center, vertical: .bottom)
        )
        .background(LinearGradient(
            gradient: Gradient(
                colors:
                    [
                        Color(.sRGB, red: 25/255, green: 117/255, blue: 210/255),
                        Color(.sRGB, red: 99/255, green: 164/255, blue: 255/255),
                    ]
            ),
            startPoint: .trailing, endPoint: .leading))
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(
                    Color(
                        .sRGB,
                        red: 150/255,
                        green: 150/255,
                        blue: 150/255,
                        opacity: 1.0
                    ),
                    lineWidth: 1
                )
        )
        .padding([.top, .horizontal])
    }

    var cityColumn: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Your Location")
                .font(Font.caption)
                .foregroundColor(Color.white)
            Text(timezone)
                .lineLimit(1)
                .font(.system(size: 16.0))
                .foregroundColor(Color.white)
        }
    }

    var dateTimeColumn: some View {
        VStack(alignment: .trailing, spacing: 8) {
            Text(time)
                .font(.system(size: 34.0))
                .foregroundColor(Color.white)
            Text(date)
                .lineLimit(1)
                .font(.system(size: 12.0))
                .foregroundColor(Color.white)
        }
    }
}

struct TimeCard_Previews: PreviewProvider {
    static var previews: some View {
        TimeCard(
            timezone: "America/Los_Angeles",
            time: "1:39 pm",
            date: "Saturday, October 16"
        )
    }
}
