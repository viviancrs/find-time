//
//  NumberTimeCard.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct NumberTimeCard: View {
    var timezone: String
    var time: String
    var hours: String
    var date: String

    var body: some View {
        HStack {
            cityView
            Spacer()
            dateTimeView
        }
        .padding(EdgeInsets(top: 0, leading: 8, bottom: 16, trailing: 8))
        .frame(
            maxWidth: .infinity,
            minHeight: 90,
            alignment: Alignment(horizontal: .leading, vertical: .bottom)
        )
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(
                    Color(
                        .sRGB,
                        red: 150/255,
                        green: 150/255,
                        blue: 150/255,
                        opacity: 1.0),
                    lineWidth: 1
                )
        )
        .padding(.leading, 16).padding(.bottom, 16).padding(.trailing, 16)
    }

    var cityView: some View {
        VStack(alignment: .leading, spacing: 32) {
            Text(timezone)
                .font(.system(size: 16.0))
                .bold()
                .foregroundColor(Color.black)
            Text(hours)
                .lineLimit(1)
                .font(.system(size: 14.0))
                .foregroundColor(Color.black)
        }
    }

    var dateTimeView: some View {
        VStack(alignment: .trailing, spacing: 32) {
            Text(time)
                .font(.system(size: 16.0))
                .bold()
                .foregroundColor(Color.black)
            Text(date)
                .lineLimit(1)
                .font(.system(size: 10.0))
                .foregroundColor(Color.black)
        }
    }
}

struct NumberTimeCard_Previews: PreviewProvider {
    static var previews: some View {
        NumberTimeCard(
            timezone: "America/Los_Angeles",
            time: "2:38 pm",
            hours: "6.0 hours from local",
            date: "Sunday, October 17"
        )
    }
}

